package com.project.spring_rest_project.service;


import com.project.spring_rest_project.entity.Customer;
import com.project.spring_rest_project.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EventService {

    @Autowired
    private CustomerService customerService;


    public Event addEvent(Long customerId, Event event) {
        Customer customer = customerService.findById(customerId);
        customer.getEvents().add(event);
        customerService.updateCustomer(customer);
        return event;
    }

}
