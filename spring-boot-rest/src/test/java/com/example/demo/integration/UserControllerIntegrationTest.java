package com.example.demo.integration;

import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest
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
    public void getUsersTest() throws Exception {
        mockMvc.perform(get("/User/get").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllUsers_OK() throws Exception {
        mockMvc.perform(get("/User/get"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Ana")))
                .andExpect(jsonPath("$[0].address", is("address")))
                .andExpect(jsonPath("$[0].age", is(22)));
    }

    @Test
    public void addUser() throws Exception {
        User user = new User("Ana", "address", 22);
        mockMvc.perform(post("/User/add")
                .content(objectMapper.writeValueAsString(user))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Ana")))
                .andExpect(jsonPath("$.address", is("address")))
                .andExpect(jsonPath("$.age", is(22)));
    }

    @Test
    public void deleteUser_OK() throws Exception {
        User user = new User(1L, "Ana", "address", 22);
        mockMvc.perform(delete("/User/delete/{id}", 4).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    //Do changes
    @Test
    public void updateUser_OK() throws Exception {
        User userUpdate = new User("Ana", "Address", 22);
        mockMvc.perform(put("/User/{id}", 4)
                .content(objectMapper.writeValueAsString(userUpdate))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserById_OK() throws Exception {
        mockMvc.perform(get("/User/get/{id}", 4))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Ana")))
                .andExpect(jsonPath("$.address", is("address")))
                .andExpect(jsonPath("$.age", is(22)));
    }

    @Test
    public void getUsersByName_OK() throws Exception {
        mockMvc.perform(get("/User/name/{name}", "Ana"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Ana")))
                .andExpect(jsonPath("$[0].address", is("address")))
                .andExpect(jsonPath("$[0].age", is(22)));
    }

}