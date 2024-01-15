package com.github.gyorgyabraham.persons.demo.controller;

import com.github.gyorgyabraham.persons.demo.model.Person;
import com.github.gyorgyabraham.persons.demo.repository.AddressRepository;
import com.github.gyorgyabraham.persons.demo.repository.ContactRepository;
import com.github.gyorgyabraham.persons.demo.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class which tests the service code in PersonControllerTest.
 */
@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonController personController;

    private static final List<Person> DEFAULT_TEST_PERSONS = new ArrayList<>();

    static {
        Person peter = new Person("Peter", "Moore");
        DEFAULT_TEST_PERSONS.add(peter);

        Person jacob = new Person("Jacob", "Smith");
        DEFAULT_TEST_PERSONS.add(jacob);
    }

    @BeforeEach
    public void setUp() {
        Mockito.when(personRepository.findAll()).thenReturn(DEFAULT_TEST_PERSONS);
    }

    @Test
    public void testAllPersonList() {
        List<Person> allPersonList = personController.getAllPersons();
        Assertions.assertTrue(allPersonList.size() == 2, "The returned person list size mismatches: " + allPersonList.size());
        Assertions.assertTrue(allPersonList.
                        stream()
                        .anyMatch(p -> p.getFirstName().equals("Peter") && p.getLastName().equals("Moore")),
                "The returned person list does not contain Peter Moore");
        Assertions.assertTrue(allPersonList
                        .stream()
                        .anyMatch(p -> p.getFirstName().equals("Jacob") && p.getLastName().equals("Smith")),
                "The returned person list does not contain Jacob Smith");
    }
}