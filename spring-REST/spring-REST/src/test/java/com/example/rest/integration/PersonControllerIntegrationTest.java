package com.example.rest.integration;


import com.example.controller.person.PersonController;
import com.example.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)

public class PersonControllerIntegrationTest {

    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PersonController personController;

    @Before
    public void setup(){
        this.mockMvc = standaloneSetup(this.personController).build();
    }

    @Test
    public void WhenPerformMethodGet_ThenShouldReturnStatusOk() throws Exception{
        mockMvc.perform(get("/person/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void GivenSavedPerson_WhenPerformMethodGetAll_ThenShouldReturnSamePerson() throws Exception{
        Person person = new Person("Joao", 21, 1);
        mockMvc.perform(post("/person/")
                .content(objectMapper.writeValueAsString(person))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/person/"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Joao")))
                .andExpect(jsonPath("$[0].age", is(21)))
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    public void GivenSavedPeople_WhenPerformMethodGetById_ThenShouldReturnRequestedPerson() throws Exception{
        Person person = new Person("Joao", 21, 1);
        Person person1 = new Person("Vitor", 22, 2);
        mockMvc.perform(post("/person/")
                .content(objectMapper.writeValueAsString(person))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(post("/person/")
                .content(objectMapper.writeValueAsString(person1))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/person/{id}", 2))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Vitor")))
                .andExpect(jsonPath("$.age", is(22)))
                .andExpect(jsonPath("$.id", is(2)));
    }

    @Test
    public void GivenSavedPerson_WhenPerformMethodCreatePerson_ThenShouldReturnStatusOk_AndSamePerson() throws Exception{
        Person person = new Person("Joao", 21, 1);
        mockMvc.perform(post("/person/")
                .content(objectMapper.writeValueAsString(person))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/person/{id}", 1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Joao")))
                .andExpect(jsonPath("$.age", is(21)))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void GivenSavedPerson_WhenPerformMethodDelete_ThenShouldReturnStatusOk() throws Exception{
        Person person = new Person("Joao", 21, 1);
        mockMvc.perform(post("/person/")
                .content(objectMapper.writeValueAsString(person))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/person/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk());
    }

    @Test
    public void GivenSavedPerson_WhenPerformMethodUpdate_ThenShouldReturnUpdatedPerson() throws Exception{
        Person person = new Person("Joao", 21, 1);
        Person person1 = new Person("Vitor", 30, 1);
        mockMvc.perform(post("/person/")
                .content(objectMapper.writeValueAsString(person))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(put("/person/{id}",1)
                .content(objectMapper.writeValueAsString(person1))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/person/{id}", 1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Vitor")))
                .andExpect(jsonPath("$.age", is(30)))
                .andExpect(jsonPath("$.id", is(1)));
    }


}
