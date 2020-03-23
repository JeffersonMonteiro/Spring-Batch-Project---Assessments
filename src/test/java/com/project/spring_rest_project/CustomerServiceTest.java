package com.project.spring_rest_project;

import com.project.spring_rest_project.entity.Customer;
import com.project.spring_rest_project.repository.CustomerRepository;
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
public class CustomerServiceTest {

    private Customer customer1;
    private Customer customer2;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService = new CustomerService(customerRepository);

    @Before
    public void setUp() {
        customer1 = new Customer((long) 1, "Ana", "ana@mail.com", "9898-9898");
        customer2 = new Customer((long) 2, "Maria", "maria@mail.com", "9797-9898");
    }

    @Test
    public void getCustomersTest() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);
        when(customerRepository.findAll()).thenReturn(customers);
        Assert.assertEquals(customers, customerService.getAll());
    }

    @Test
    public void createCustomersTest() {
        when(customerRepository.save(customer1)).thenReturn(customer1);
        Assert.assertEquals(customer1, customerService.createCustomer(customer1));
    }

    @Test
    public void removeCustomersTest() {
        customerRepository.deleteById(customer1.getCustomerId());
        verify(customerRepository).deleteById(customer1.getCustomerId());
    }
//
//    @Test
//    public void updateCustomersTest() {
//        customerRepository.deleteById(customer1.getCustomerId());
//        verify(customerRepository).deleteById(customer1.getCustomerId());
//    }
//
//    @Test
//    public void findCustomerByIdTest() {
//        customerRepository.deleteById(customer1.getCustomerId());
//        verify(customerRepository).deleteById(customer1.getCustomerId());
//    }
}
