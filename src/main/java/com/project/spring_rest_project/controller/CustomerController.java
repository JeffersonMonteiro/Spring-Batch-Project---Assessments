package com.project.spring_rest_project.controller;


import com.project.spring_rest_project.entity.Customer;
import com.project.spring_rest_project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public Iterable<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{customerId}")
    public Customer addById(@PathVariable Long customerId) {
        return customerService.findById(customerId);
    }

    @PostMapping("/add")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @DeleteMapping("/{customerId}")
    public void removeCustomer(@PathVariable Long customerId) {
        customerService.removeById(customerId);
    }

    @PutMapping("/{customerId}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable Long customerId) {
        return customerService.updateCustomer(customer);
    }
}
