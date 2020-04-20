package com.example.demo.IntegrationTests;

import com.example.demo.Controller.ActivityController;
import com.example.demo.Controller.VolunteerController;
import com.example.demo.Entity.Activity;
import com.example.demo.Entity.Volunteer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ActivityControllerIntegrationTest {

    private MockMvc mockMvc;
    private MockMvc mockVolunteer;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ActivityController activityController;

    @Autowired
    private VolunteerController volunteerController;


    @Before
    public void setup() {
        this.mockVolunteer = standaloneSetup(this.volunteerController).build();
        this.mockMvc = standaloneSetup(this.activityController).build();
    }

    @Test
    public void whenIRequestMethodGet_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/activity/get").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenActivity_GivenVolunteer_WhenISave_ShouldReturnOk() throws Exception {
        Volunteer volunteer = new Volunteer(1, "Leia", 21, 15, 5);
        Activity activity = new Activity(2, "CC", "1509", "Caximba");

        mockVolunteer.perform(post("/volunteer/add").content(objectMapper.writeValueAsString(volunteer))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(post("/activity/add/{volunteerId}", 1).content(objectMapper.writeValueAsString(activity))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/activity/get"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].code", is("CC")))
                .andExpect(jsonPath("$[0].dateCode", is("1509")))
                .andExpect(jsonPath("$[0].slum", is("Caximba")));
    }

    @Test
    public void givenActivityAndVolunteer_WhenIGetById_ShouldReturnActivityWithSameId() throws Exception {
        Volunteer volunteer = new Volunteer(1, "Leia", 21, 15, 5);
        Activity activity = new Activity(2, "CC", "1509", "Caximba");

        mockVolunteer.perform(post("/volunteer/add").content(objectMapper.writeValueAsString(volunteer))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(post("/activity/add/{volunteerId}", 1).content(objectMapper.writeValueAsString(activity))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/activity/get/{id}", 2))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.code", is("CC")))
                .andExpect(jsonPath("$.dateCode", is(1509)))
                .andExpect(jsonPath("$.slum", is("Caximba")));
    }

    @Test
    public void givenActivityAndVolunteer_WhenIUpdateActivity_ShouldReturnUpdatedActivity() throws Exception {
        Volunteer volunteer = new Volunteer(1, "Leia", 21, 15, 5);
        Activity activity = new Activity(2, "CC", "1509", "Caximba");
        Activity updtActivity = new Activity(2, "CC", "1509", "Portelinha");

        mockVolunteer.perform(post("/volunteer/add").content(objectMapper.writeValueAsString(volunteer))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(post("/activity/add/{volunteerId}", 1).content(objectMapper.writeValueAsString(activity))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(put("/activity/update/{id}", 2).content(objectMapper.writeValueAsString(updtActivity))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/activity/get/{id}", 2))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.code", is("CC")))
                .andExpect(jsonPath("$.dateCode", is("1509")))
                .andExpect(jsonPath("$.slum", is("Portelinha")));

    }

    @Test
    public void givenActivityAndVolunteer_WhenIDeleteActivity_ShouldReturnOk() throws Exception {
        Volunteer volunteer = new Volunteer(1, "Leia", 21, 15, 5);
        Activity activity = new Activity(2, "CC", "1509", "Caximba");

        mockVolunteer.perform(post("/volunteer/add").content(objectMapper.writeValueAsString(volunteer))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(post("/activity/add/{volunteerId}", 1).content(objectMapper.writeValueAsString(activity))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/activity/delete/{id}/{volunteerId}", 2, 1).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(activity)))
                .andExpect(status().isOk());
    }
}