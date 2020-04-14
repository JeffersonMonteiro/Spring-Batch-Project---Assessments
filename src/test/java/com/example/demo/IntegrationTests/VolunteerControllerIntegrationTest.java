package com.example.demo.IntegrationTests;

import com.example.demo.Controller.VolunteerController;
import com.example.demo.Entity.Volunteer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class VolunteerControllerIntegrationTest {

   MockMvc mockMvc;

   @Autowired
   private ObjectMapper objectMapper;

   @Autowired
   private VolunteerController volunteerController;

   @Before
   public void setup(){
       this.mockMvc = standaloneSetup(this.volunteerController).build();
   }

   @Test
   public void whenIRequestGet_ThenShouldReturnOk() throws Exception {
       mockMvc.perform(get("/volunteer/get").contentType(MediaType.APPLICATION_JSON))
       .andExpect(status().isOk());
    }

    @Test
    public void givenVolunteer_WhenISave_ShouldReturnOk() throws Exception {
        Volunteer volunteer = new Volunteer(1,"Leia", 21, 15, 5);

        mockMvc.perform(post("/volunteer/add").content(objectMapper.writeValueAsString(volunteer))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/volunteer/get"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Leia")))
                .andExpect(jsonPath("$[0].age", is(21)))
                .andExpect(jsonPath("$[0].amntBuilding", is(15)))
                .andExpect(jsonPath("$[0].amntSurvey", is(5)));
    }

    @Test
    public void givenSavedVolunteer_WhenIDeleteIt_ShouldReturnOk()  throws Exception{
        Volunteer volunteer = new Volunteer(1,"Leia", 21, 15, 5);

        mockMvc.perform(post("/volunteer/add").content(objectMapper.writeValueAsString(volunteer))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/volunteer/delete/{id}", 1).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(volunteer)))
                .andExpect(status().isOk());
    }

    @Test
    public void givenSavedVolunteer_WhenIGetById_ShouldReturnVolunteerWithSameId() throws Exception{
        Volunteer volunteer = new Volunteer(1,"Leia", 21, 15, 5);

        mockMvc.perform(post("/volunteer/add").content(objectMapper.writeValueAsString(volunteer))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/volunteer/get/{id}", 1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Leia")))
                .andExpect(jsonPath("$.age", is(21)))
                .andExpect(jsonPath("$.amntBuilding", is(15)))
                .andExpect(jsonPath("$.amntSurvey", is(5)));
    }

    @Test
    public void givenVolunteer_WhenIUpdate_ShouldReturnUpdatedVolunteer() throws Exception {
        Volunteer volunteer = new Volunteer(1, "Leia", 21, 15, 5);
        Volunteer updtvolunteer = new Volunteer(1, "Leia", 22, 15, 7);

        mockMvc.perform(post("/volunteer/add").content(objectMapper.writeValueAsString(volunteer))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(put("/volunteer/update/{id}", 1).content(objectMapper.writeValueAsString(updtvolunteer))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/volunteer/get/{id}", 1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Leia")))
                .andExpect(jsonPath("$.age", is(22)))
                .andExpect(jsonPath("$.amntBuilding", is(15)))
                .andExpect(jsonPath("$.amntSurvey", is(7)));
    }


}
