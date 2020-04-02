package com.example.demo.integration;

import com.example.demo.controller.ProductController;
import com.example.demo.controller.UserController;
import com.example.demo.model.Product;
import com.example.demo.model.User;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ProductControllerIntegrationTest {

    MockMvc mockMvc;
    MockMvc mockMvcUser;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductController productController;

    @Autowired
    private UserController userController;

    @Before
    public void setup() {
        this.mockMvcUser = standaloneSetup(this.userController).build();
        this.mockMvc = standaloneSetup(this.productController).build();
    }

    @Test
    public void GivenAUserAndAProduct_WhenSaveThisUserAndProduct_ThenShouldReturnOk() throws Exception {
        User user = new User("Ana", "address", 22);
        mockMvcUser.perform(post("/User/add")
                .content(objectMapper.writeValueAsString(user))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Product product = new Product("Dress");
        mockMvc.perform(post("/User/1/Product/add")
                .content(objectMapper.writeValueAsString(product))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void GivenAProductSaved_WhenDeleteThisProduct_ThenShouldReturnOk() throws Exception {
        User user = new User("Ana", "address", 22);
        mockMvcUser.perform(post("/User/add")
                .content(objectMapper.writeValueAsString(user))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Product product = new Product("Dress");
        mockMvc.perform(post("/User/1/Product/add")
                .content(objectMapper.writeValueAsString(product))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/User/1/Product/delete/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());
    }

}
