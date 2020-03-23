package com.project.spring_rest_project.service;


import com.project.spring_rest_project.entity.Customer;
import com.project.spring_rest_project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

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


    public Customer updateCustomer(Customer customer) {
        Optional<Customer> OptCustomer = customerRepository.findById(customer.getCustomerId());

        if(OptCustomer.isPresent()) {
            customer.setCustomerId(customer.getCustomerId());
            return customerRepository.save(customer);
        }

        return OptCustomer.orElseThrow(() -> new RuntimeException("Customer does not exist"));
    }
}
