package com.project.spring_rest_project;


import com.project.spring_rest_project.controller.CustomerController;
import com.project.spring_rest_project.entity.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
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
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
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
        Customer customer = new Customer("Ana", "ana@mail.com", "9898-9898");
        mockMvc.perform(post("/customer/add").content(objectMapper.writeValueAsString(customer))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/customer")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void GivenACustomer_WhenISaveACustomer_ThenShouldReturnACustomer() throws Exception {
       Customer customer = new Customer("Ana", "ana@mail.com", "9898-9898");
       mockMvc.perform(post("/customer/add").content(objectMapper.writeValueAsString(customer))
               .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId", is(1)))
                .andExpect(jsonPath("$.customerName", is("Ana")))
                .andExpect(jsonPath("$.customerMail", is("ana@mail.com")))
                .andExpect(jsonPath("$.customerPhoneNumber", is("9898-9898")));
    }

    @Test
    public void GivenASavedCustomer_WhenIDeleteThisCustomer_ThenShouldReturnCustomerDeleted() throws Exception {
        Customer customer = new Customer("Ana", "ana@mail.com", "9898-9898");
        mockMvc.perform(post("/customer/add")
                .content(objectMapper.writeValueAsString(customer))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(delete("/customer/{customerId}", 1).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk());
    }

    @Test
    public void GivenACustomer_WhenIUpdateThisCustomer_ThenShouldReturnStatusOk() throws Exception {
        Customer customer = new Customer(1L,"Ana", "ana@mail.com", "9898-9898");
        Customer customer2 = new Customer(1L,"Bruno", "ana@mail.com", "9898-9898");
        mockMvc.perform(post("/customer/add").content(objectMapper.writeValueAsString(customer))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId", is(1)));
        mockMvc.perform(put("/customer/{customerId}", 1)
                .content(objectMapper.writeValueAsString(customer2))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
