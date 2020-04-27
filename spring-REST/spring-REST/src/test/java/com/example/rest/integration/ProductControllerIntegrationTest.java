package com.example.rest.integration;


import com.example.controller.person.PersonController;
import com.example.controller.person.ProductController;
import com.example.model.Person;
import com.example.model.Product;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    private PersonController personController;

    @Before
    public void setup() {
        this.mockMvcUser = standaloneSetup(this.personController).build();
        this.mockMvc = standaloneSetup(this.productController).build();
    }

    @Test
    public void GivenSavedPerson_WhenPerformMethodCreateProduct_ThenShouldReturnStatusOk() throws Exception{
        Person person = new Person("Joao", 21, 1);
        mockMvc.perform(post("/person/")
                .content(objectMapper.writeValueAsString(person))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Product product = new Product("Chapeu");
        mockMvc.perform(post("/person/{id}/product/", 1)
                .content(objectMapper.writeValueAsString(product))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
