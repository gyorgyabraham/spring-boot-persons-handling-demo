package com.github.gyorgyabraham.persons.demo.controller;

import com.github.gyorgyabraham.persons.demo.repository.AddressRepository;
import com.github.gyorgyabraham.persons.demo.repository.ContactRepository;
import com.github.gyorgyabraham.persons.demo.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.gyorgyabraham.persons.demo.controller.TestUtils.DEFAULT_TEST_PERSONS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration test class that tests web endpoints of PersonController.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class)
public class PersonControllerWebTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private ContactRepository contactRepository;

    @BeforeEach
    public void setUp() {
        Mockito.when(personRepository.findAll()).thenReturn(DEFAULT_TEST_PERSONS);
    }

    @Test
    public void testGetAllPersons() throws Exception {
        mockMvc.perform(get("/api/v1/persons"))
                .andExpect(status().isOk());
    }
}