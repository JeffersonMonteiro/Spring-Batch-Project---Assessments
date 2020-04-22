package com.project.spring_rest_project;


import com.project.spring_rest_project.controller.CustomerController;
import com.project.spring_rest_project.entity.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTest {

    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerController customerController;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(this.customerController).build();
    }

    @Test
    public void WhenTheMethodGetCustomers_ThenShouldReturnStatusOk() throws Exception {
        mockMvc.perform(get("/Customer/get")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void GivenACustomer_WhenISaveACustomer_ThenShouldReturnACustomer() throws Exception {
       Customer customer = new Customer("Ana", "ana@mail.com", "9898-9898");
       mockMvc.perform(post("/Customer/add").content(objectMapper.writeValueAsString(customer))
               .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName", is("Ana")))
                .andExpect(jsonPath("$.customerMail", is("ana@mail.com")))
                .andExpect(jsonPath("$.customerPhoneNumber", is("9898-9898")));
    }

    @Test
    public void GivenASavedCustomer_WhenIDeleteThisCustomer_ThenShouldReturnCustomerDeleted() throws Exception{
        Customer customer = new Customer("Ana", "ana@mail.com", "9898-9898");
        mockMvc.perform(post("/Customer/Add").content(objectMapper.writeValueAsString(customer))
                .header((HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName", is("Ana")))
                .andExpect(jsonPath("$.customerMail", is("ana@mail.com")))
                .andExpect(jsonPath("$.customerPhoneNumber", is("9898-9898")));


    }

}
