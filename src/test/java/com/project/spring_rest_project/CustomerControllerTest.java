package com.project.spring_rest_project;


import com.project.spring_rest_project.controller.CustomerController;
import com.project.spring_rest_project.entity.Customer;
import com.project.spring_rest_project.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerControllerTest {

    private Customer customer;

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    @Before
    public void setUp() {
        customer = new Customer((long) 1, "Ana", "ana@mail.com", "9898-9898");
    }

    @Test
    public void getAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(customerService.getAll()).thenReturn(customers);
        Assert.assertEquals(customers, customerController.getAll());
    }

    @Test
    public void getCustomerById() {
        when(customerService.findById(customer.getCustomerId())).thenReturn(customer);
        Assert.assertEquals(customer, customerController.addById(customer.getCustomerId()));
    }

    @Test
    public void addCustomer() {
        when(customerService.createCustomer(customer)).thenReturn(customer);
        Assert.assertEquals(customer, customerController.createCustomer(customer));
    }

    @Test
    public void deleteCustomer() {
        customerController.removeCustomer(customer.getCustomerId());
        verify(customerService).removeById(customer.getCustomerId());
    }

    @Test
    public void updateCustomerByIdTest() {
        when(customerController.updateCustomer(customer, customer.getCustomerId())).thenReturn(customer);
        Assert.assertEquals(customer, customerService.updateCustomer(customer));
    }
}
