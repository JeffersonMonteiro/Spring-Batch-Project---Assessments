package com.project.spring_rest_project.service;


import com.project.spring_rest_project.entity.Customer;
import com.project.spring_rest_project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Iterable<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findById(Long customerId) {
        Optional<Customer> optionalCustomers = customerRepository.findById(customerId);

        return optionalCustomers.orElseThrow(() -> new RuntimeException("Customer does not exist"));
    }

    public void removeById(Long customerId) {
        customerRepository.deleteById(customerId);
    }


    public Customer updateCustomer(Customer customer, Long customerId) {
        Optional<Customer> OptCustomer = customerRepository.findById(customerId);

        if(OptCustomer.isPresent()) {
            customer.setCustomerId(customerId);
            return customerRepository.save(customer);
        }

        return OptCustomer.orElseThrow(() -> new RuntimeException("Customer does not exist"));
    }
}
