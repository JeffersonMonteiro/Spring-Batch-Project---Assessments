package com.project.spring_rest_project.controller;


import com.project.spring_rest_project.entity.Customer;
import com.project.spring_rest_project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Iterable<Customer> addAll() {
        return customerService.addAll();
    }

    @GetMapping("/{customerId}")
    public Customer addById(@PathVariable Long customerId) {
        return customerService.findById(customerId);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @DeleteMapping("/{customerId}")
    public void removeCustomer(@PathVariable Long customerId) {
        customerService.removeById(customerId);
    }

    @PutMapping
    public void updateCustomer(@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
    }
}
