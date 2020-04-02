package com.example.demo.integration;

import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class UserControllerIntegrationTest {

    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserController userController;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(this.userController).build();
    }

    @Test
    public void WhenTheMethodGet_ThenShouldReturnStatusOk() throws Exception {
        mockMvc.perform(get("/User/get").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void GivenAUser_WhenISave_TheMethodGetAllUser_ThenShouldReturnTheSameUser() throws Exception {
        User user = new User("Ana", "address", 22);
        mockMvc.perform(post("/User/add")
                .content(objectMapper.writeValueAsString(user))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/User/get"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Ana")))
                .andExpect(jsonPath("$[0].address", is("address")))
                .andExpect(jsonPath("$[0].age", is(22)))
                .andExpect(jsonPath("$[0].idUser", is(1)));
    }

    @Test
    public void GivenAUser_WhenSave_ThenReturnTheSameUserWithId1() throws Exception {
        User user = new User("Ana", "address", 22);
        mockMvc.perform(post("/User/add")
                .content(objectMapper.writeValueAsString(user))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/User/get/{id}", 1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Ana")))
                .andExpect(jsonPath("$.address", is("address")))
                .andExpect(jsonPath("$.age", is(22)));
    }

    @Test
    public void GivenAUserSaved_WhenDeleteThisUser_ThenShouldReturnOk() throws Exception {
        User user = new User(1L, "Ana", "address", 22);
        mockMvc.perform(post("/User/add")
                .content(objectMapper.writeValueAsString(user))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/User/delete/{id}", 1).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void GivenAUser_WhenIGetById_ThenShouldReturnTheSameID() throws Exception {
        User user = new User("Ana", "address", 22);
        mockMvc.perform(post("/User/add")
                .content(objectMapper.writeValueAsString(user))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/User/get/{id}", 1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Ana")))
                .andExpect(jsonPath("$.address", is("address")))
                .andExpect(jsonPath("$.age", is(22)));
    }

    @Test
    public void GivenAName_WhenIFindByName_ThenShouldReturnAListWhitTheUser() throws Exception {
        User user = new User("Ana", "address", 22);
        mockMvc.perform(post("/User/add")
                .content(objectMapper.writeValueAsString(user))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/User/name/{name}", "Ana"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Ana")))
                .andExpect(jsonPath("$[0].address", is("address")))
                .andExpect(jsonPath("$[0].age", is(22)));
    }

}