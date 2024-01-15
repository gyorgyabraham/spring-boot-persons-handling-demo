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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class that tests web endpoints of PersonController.
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
        Mockito.when(personRepository.findById(Long.valueOf(0))).thenReturn(Optional.of(DEFAULT_TEST_PERSONS.get(0)));
        Mockito.when(personRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(DEFAULT_TEST_PERSONS.get(1)));
        Mockito.when(personRepository.findById(Long.valueOf(2))).thenReturn(Optional.of(DEFAULT_TEST_PERSONS.get(2)));
    }

    @Test
    public void testGetAllPersons() throws Exception {
        mockMvc.perform(get("/api/v1/persons"))
                .andExpect(content().json("[\n" +
                        "  {\n" +
                        "    \"id\": 0,\n" +
                        "    \"firstName\": \"Peter\",\n" +
                        "    \"lastName\": \"Moore\",\n" +
                        "    \"permanentAddress\": {\n" +
                        "      \"id\": 0,\n" +
                        "      \"country\": \"Hungary\",\n" +
                        "      \"city\": \"Budapest\",\n" +
                        "      \"street\": \"Puli setany\",\n" +
                        "      \"houseNr\": \"1\",\n" +
                        "      \"zipcode\": \"1213\"\n" +
                        "    },\n" +
                        "    \"temporaryAddress\": {\n" +
                        "      \"id\": 0,\n" +
                        "      \"country\": \"Hungary\",\n" +
                        "      \"city\": \"Budapest\",\n" +
                        "      \"street\": \"Cirmos setany\",\n" +
                        "      \"houseNr\": \"3\",\n" +
                        "      \"zipcode\": \"1213\"\n" +
                        "    },\n" +
                        "    \"contacts\": [\n" +
                        "      {\n" +
                        "        \"type\": \"emailContact\",\n" +
                        "        \"emailAddress\": \"peter@puli.com\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"type\": \"phoneContact\",\n" +
                        "        \"phoneNumber\": null\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 0,\n" +
                        "    \"firstName\": \"Jacob\",\n" +
                        "    \"lastName\": \"Smith\",\n" +
                        "    \"permanentAddress\": null,\n" +
                        "    \"temporaryAddress\": null,\n" +
                        "    \"contacts\": []\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"id\": 0,\n" +
                        "    \"firstName\": \"Gipsz\",\n" +
                        "    \"lastName\": \"Jakab\",\n" +
                        "    \"permanentAddress\": null,\n" +
                        "    \"temporaryAddress\": null,\n" +
                        "    \"contacts\": []\n" +
                        "  }\n" +
                        "]"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPersonDetail() throws Exception {
        mockMvc.perform(get("/api/v1/persons/0"))
                .andExpect(content().json("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"firstName\": \"Peter\",\n" +
                        "  \"lastName\": \"Moore\",\n" +
                        "  \"permanentAddress\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"country\": \"Hungary\",\n" +
                        "    \"city\": \"Budapest\",\n" +
                        "    \"street\": \"Puli setany\",\n" +
                        "    \"houseNr\": \"1\",\n" +
                        "    \"zipcode\": \"1213\"\n" +
                        "  },\n" +
                        "  \"temporaryAddress\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"country\": \"Hungary\",\n" +
                        "    \"city\": \"Budapest\",\n" +
                        "    \"street\": \"Cirmos setany\",\n" +
                        "    \"houseNr\": \"3\",\n" +
                        "    \"zipcode\": \"1213\"\n" +
                        "  },\n" +
                        "  \"contacts\": [\n" +
                        "    {\n" +
                        "      \"type\": \"emailContact\",\n" +
                        "      \"emailAddress\": \"peter@puli.com\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"type\": \"phoneContact\",\n" +
                        "      \"phoneNumber\": null\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/v1/persons/1"))
                .andExpect(content().json("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"firstName\": \"Jacob\",\n" +
                        "  \"lastName\": \"Smith\",\n" +
                        "  \"permanentAddress\": null,\n" +
                        "  \"temporaryAddress\": null,\n" +
                        "  \"contacts\": []\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreatePerson() throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("firstName", "Gipsz");
        body.put("lastName", "Jakab");

        Map<String, Object> invalidBody = new HashMap<>();
        body.put("firstName", "Gipsz");

        // empty body
        mockMvc.perform(post("/api/v1/persons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // missing field in body
        mockMvc.perform(post("/api/v1/persons")
                        .content(objectMapper.writeValueAsString(invalidBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // bad media type
        mockMvc.perform(post("/api/v1/persons")
                        .content(objectMapper.writeValueAsString(body))
                        .contentType(MediaType.APPLICATION_PDF_VALUE))
                .andExpect(status().isUnsupportedMediaType());

        // correct request
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

        Map<String, Object> invalidBody = new HashMap<>();
        body.put("firstName", "Gipsz");

        // empty body
        mockMvc.perform(put("/api/v1/persons/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // missing field in body
        mockMvc.perform(put("/api/v1/persons/2")
                        .content(objectMapper.writeValueAsString(invalidBody))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // bad media type
        mockMvc.perform(put("/api/v1/persons/2")
                        .content(objectMapper.writeValueAsString(body))
                        .contentType(MediaType.APPLICATION_PDF_VALUE))
                .andExpect(status().isUnsupportedMediaType());

        // correct request
        mockMvc.perform(put("/api/v1/persons/2")
                        .content(objectMapper.writeValueAsString(body))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePerson() throws Exception {
        mockMvc.perform(delete("/api/v1/persons/2"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCallInvalidUrl() throws Exception {
        // some non existing uri
        mockMvc.perform(get("/api/v1/some-non-existing-url"))
                .andExpect(status().isNotFound());

        // various unsupported methods
        mockMvc.perform(delete("/api/v1/persons"))
                .andExpect(status().isMethodNotAllowed());

        mockMvc.perform(post("/api/v1/persons/1"))
                .andExpect(status().isMethodNotAllowed());

        mockMvc.perform(put("/api/v1/persons"))
                .andExpect(status().isMethodNotAllowed());
    }
}