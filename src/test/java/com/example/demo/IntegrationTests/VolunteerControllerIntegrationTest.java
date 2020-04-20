package com.example.demo.IntegrationTests;

import com.example.demo.Controller.VolunteerController;
import com.example.demo.Entity.Volunteer;
import com.example.demo.Exception.APIException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class VolunteerControllerIntegrationTest {

   private MockMvc mockMvc;


   @Autowired
   private ObjectMapper objectMapper;

   @Autowired
   private VolunteerController volunteerController;

   @Before
   public void setup(){
       this.mockMvc = standaloneSetup(this.volunteerController).build();
       ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
   }



   @Test
   public void whenIRequestGet_ThenShouldReturnOk() throws Exception {
       mockMvc.perform(get("/volunteer/get").contentType(MediaType.APPLICATION_JSON))
       .andExpect(status().isOk());
    }

    @Test
    public void givenVolunteer_WhenISave_ShouldReturnVolunteer() throws Exception {
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

            // testar cenários negativos
            // implementar requisitos no service (not nulls, idade etc)

    @Test(expected = APIException.class)
    public void GivenNoNameOnVolunteer_WhenICreateVolunteer_ShouldReturnError() throws Exception {
        Volunteer volunteer = new Volunteer();
        volunteer.setId(1);
        volunteer.setAge(20);
        volunteer.setAmntBuilding(10);
        volunteer.setAmntBuilding(2);

        try {
            mockMvc.perform(post("/volunteer/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(volunteer))
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        } catch (NestedServletException e){
            Assert.assertEquals("All fields are mandatory", e.getCause().getMessage());
            throw (Exception) e.getCause();
          }
    }

    @Test(expected = APIException.class)
    public void GivenVolunteerIsUnderage_WhenISave_ShouldReturnError() throws Exception{
        Volunteer volunteer = new Volunteer(1,"Leia", 17, 15, 5);

        try {
            mockMvc.perform(post("/volunteer/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(volunteer))
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        } catch (NestedServletException e){
            Assert.assertEquals("Volunteer cannot be underage", e.getCause().getMessage());
            throw (Exception) e.getCause();
        }
    }
}
