package com.github.gyorgyabraham.persons.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.github.gyorgyabraham.persons.demo.controller.TestUtils.DEFAULT_TEST_PERSONS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        Mockito.when(personRepository.findAll()).thenReturn(DEFAULT_TEST_PERSONS);
        Mockito.when(personRepository.findById(Mockito.any())).thenReturn(Optional.of(DEFAULT_TEST_PERSONS.get(1)));
    }

    @Test
    public void testGetAllPersons() throws Exception {
        mockMvc.perform(get("/api/v1/persons"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPersonDetail() throws Exception {
        mockMvc.perform(get("/api/v1/persons/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreatePerson() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("firstName", "Gipsz");
        body.put("lastName", "Jakab");

        mockMvc.perform(post("/api/v1/persons")
                        .content(objectMapper.writeValueAsString(body))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdatePerson() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("firstName", "Gipsz");
        body.put("lastName", "Jakab");

        mockMvc.perform(put("/api/v1/persons/1")
                        .content(objectMapper.writeValueAsString(body))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}