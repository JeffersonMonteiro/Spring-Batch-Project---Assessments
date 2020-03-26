package com.example.demo;

import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.mockito.Mockito.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerIntegrationTest {

    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserController userController;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.userController).build();
    }

    @Test
    public void getUsersTest() throws Exception {
        mockMvc.perform(get("/User/get").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllUsers_OK() throws Exception {
        List<User> users = Arrays.asList(
                new User(1L, "Ana", "Address", 21),
                new User(2L, "Amanda", "Address", 22)
        );
        when(userRepository.findAll()).thenReturn(users);
        mockMvc.perform(get("/User/get"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Ana")))
                .andExpect(jsonPath("$[0].address", is("Address")))
                .andExpect(jsonPath("$[0].age", is(21)))
                .andExpect(jsonPath("$[0].idUser", is(1)))
                .andExpect(jsonPath("$[1].name", is("Amanda")))
                .andExpect(jsonPath("$[1].address", is("Address")))
                .andExpect(jsonPath("$[1].age", is(22)))
                .andExpect(jsonPath("$[1].idUser", is(2)));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void addUser() throws Exception {
        User user = new User(1L, "Ana", "address", 22);
        when(userRepository.save(any(User.class))).thenReturn(user);
        mockMvc.perform(post("/User/add")
                .content(objectMapper.writeValueAsString(user))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void deleteUser_OK() throws Exception {
        User user = new User(1L, "Ana", "address", 22);
        mockMvc.perform(delete("/User/delete/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void updateUser_OK() throws Exception {
        User userUpdate = new User(1L, "Ana", "address", 22);
        when(userRepository.save(any(User.class))).thenReturn(userUpdate);
        mockMvc.perform(put("/User/{id}", 1L)
                .content(objectMapper.writeValueAsString(userUpdate))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
