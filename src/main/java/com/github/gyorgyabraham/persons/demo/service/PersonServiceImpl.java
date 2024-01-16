package com.github.gyorgyabraham.persons.demo.service;

import com.github.gyorgyabraham.persons.demo.exception.ResourceNotFoundException;
import com.github.gyorgyabraham.persons.demo.model.Address;
import com.github.gyorgyabraham.persons.demo.model.Contact;
import com.github.gyorgyabraham.persons.demo.model.Person;
import com.github.gyorgyabraham.persons.demo.repository.AddressRepository;
import com.github.gyorgyabraham.persons.demo.repository.ContactRepository;
import com.github.gyorgyabraham.persons.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ContactRepository contactRepository;


    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(Long personId) throws ResourceNotFoundException {
        Person person = personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));
        return person;
    }

    @Override
    public Person createPerson(Person person) {
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
        return person;
    }

    @Override
    public Person updatePerson(Long personId, Person personDetails) throws ResourceNotFoundException {
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
        if (null != newPermanentAddress) {
            newPermanentAddress.setOwningPerson(existingPerson);
            existingPerson.setPermanentAddress(newPermanentAddress);
        }

        Address newTemporaryAddress = personDetails.getTemporaryAddress();
        if (null != newTemporaryAddress) {
            newTemporaryAddress.setOwningPerson(existingPerson);
            existingPerson.setTemporaryAddress(newTemporaryAddress);
        }

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
        return updatedPerson;
    }

    @Override
    public Map<String, Boolean> deletePerson(Long personId) throws ResourceNotFoundException {
        Person person = personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("Person not found for this id :: " + personId));

        personRepository.delete(person);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
