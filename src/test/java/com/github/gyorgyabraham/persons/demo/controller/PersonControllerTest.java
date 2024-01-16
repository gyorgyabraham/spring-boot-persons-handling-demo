package com.github.gyorgyabraham.persons.demo.controller;

import com.github.gyorgyabraham.persons.demo.model.Person;
import com.github.gyorgyabraham.persons.demo.repository.PersonRepository;
import com.github.gyorgyabraham.persons.demo.service.PersonService;
import com.github.gyorgyabraham.persons.demo.service.PersonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.github.gyorgyabraham.persons.demo.controller.TestUtils.DEFAULT_TEST_PERSONS;

/**
 * Test class for {@link PersonService}.
 */
@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @BeforeEach
    public void setUp() {
        Mockito.when(personRepository.findAll()).thenReturn(DEFAULT_TEST_PERSONS);
    }

    @Test
    public void testAllPersonList() {
        List<Person> allPersonList = personService.getAllPersons();
        Assertions.assertTrue(allPersonList.size() == 3, "The returned person list size mismatches: " + allPersonList.size());
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