package com.github.gyorgyabraham.persons.demo.service;

import com.github.gyorgyabraham.persons.demo.exception.ResourceNotFoundException;
import com.github.gyorgyabraham.persons.demo.model.Person;

import java.util.List;
import java.util.Map;

public interface PersonService {
    List<Person> getAllPersons();

    Person getPersonById(Long personId) throws ResourceNotFoundException;

    Person createPerson(Person person);

    Person updatePerson(Long personId, Person personDetails) throws ResourceNotFoundException;

    Map<String, Boolean> deletePerson(Long personId) throws ResourceNotFoundException;
}
