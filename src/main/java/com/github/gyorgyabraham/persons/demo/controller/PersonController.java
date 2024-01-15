package com.github.gyorgyabraham.persons.demo.controller;

import com.github.gyorgyabraham.persons.demo.exception.ResourceNotFoundException;
import com.github.gyorgyabraham.persons.demo.model.Address;
import com.github.gyorgyabraham.persons.demo.model.Contact;
import com.github.gyorgyabraham.persons.demo.model.Person;
import com.github.gyorgyabraham.persons.demo.repository.AddressRepository;
import com.github.gyorgyabraham.persons.demo.repository.ContactRepository;
import com.github.gyorgyabraham.persons.demo.repository.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long personId) throws ResourceNotFoundException {
        Person person = personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));
        return ResponseEntity.ok().body(person);
    }

    @PostMapping("/persons")
    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person, Errors errors) {
        personRepository.save(person);

        Address permanentAddress = person.getPermanentAddress();
        if (permanentAddress != null) {
            permanentAddress.setOwningPerson(person);
            addressRepository.save(permanentAddress);
        }
        Address temporaryAddress = person.getTemporaryAddress();
        if (temporaryAddress != null) {
            temporaryAddress.setOwningPerson(person);
            addressRepository.save(temporaryAddress);
        }

        person.getContacts().stream().forEach(contact -> contact.setOwningPerson(person));
        contactRepository.saveAll(person.getContacts());
        return new ResponseEntity<Person>(person, HttpStatus.CREATED);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") Long personId, @Valid @RequestBody Person personDetails) throws ResourceNotFoundException {
        Person existingPerson = personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));

        existingPerson.setFirstName(personDetails.getFirstName());
        existingPerson.setLastName(personDetails.getLastName());

        // delete old address entities to prevent flooding them in DB
        Address existingPermanentAddress = existingPerson.getPermanentAddress();
        if (existingPermanentAddress != null) {
            existingPermanentAddress.setOwningPerson(null);
            existingPerson.setPermanentAddress(null);
            addressRepository.delete(existingPermanentAddress);
        }
        Address existingTemporaryAddress = existingPerson.getTemporaryAddress();
        if (existingTemporaryAddress != null) {
            existingTemporaryAddress.setOwningPerson(null);
            existingPerson.setTemporaryAddress(null);
            addressRepository.delete(existingTemporaryAddress);
        }

        // add new address entities
        Address newPermanentAddress = personDetails.getPermanentAddress();
        newPermanentAddress.setOwningPerson(existingPerson);
        existingPerson.setPermanentAddress(newPermanentAddress);
        Address newTemporaryAddress = personDetails.getTemporaryAddress();
        newTemporaryAddress.setOwningPerson(existingPerson);
        existingPerson.setTemporaryAddress(newTemporaryAddress);

        // delete old contact entities to prevent flooding them in DB
        List<Contact> existingContacts = existingPerson.getContacts();
        existingContacts.forEach(c -> {
            c.setOwningPerson(null);
        });
        existingPerson.setContacts(new ArrayList<>());
        contactRepository.deleteAll(existingContacts);

        // set other side of the relation for new contacts...
        personDetails.getContacts().forEach(c -> c.setOwningPerson(existingPerson));
        // ...and save them
        existingPerson.setContacts(personDetails.getContacts());

        final Person updatedPerson = personRepository.save(existingPerson);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/persons/{id}")
    public Map<String, Boolean> deletePerson(@PathVariable(value = "id") Long personId) throws ResourceNotFoundException {
        Person person = personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));

        personRepository.delete(person);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
