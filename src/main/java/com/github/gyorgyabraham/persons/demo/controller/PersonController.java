package com.github.gyorgyabraham.persons.demo.controller;

import com.github.gyorgyabraham.persons.demo.exception.ResourceNotFoundException;
import com.github.gyorgyabraham.persons.demo.model.Person;
import com.github.gyorgyabraham.persons.demo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long personId) throws ResourceNotFoundException {
        Person person = personService.getPersonById(personId);
        return ResponseEntity.ok().body(person);
    }

    @PostMapping("/persons")
    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person) {
        Person savedPerson = personService.createPerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") Long personId, @Valid @RequestBody Person personDetails) throws ResourceNotFoundException {
        final Person updatedPerson = personService.updatePerson(personId, personDetails);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/persons/{id}")
    public Map<String, Boolean> deletePerson(@PathVariable(value = "id") Long personId) throws ResourceNotFoundException {
        return personService.deletePerson(personId);
    }
}
