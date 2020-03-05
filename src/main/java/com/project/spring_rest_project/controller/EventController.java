package com.project.spring_rest_project.controller;


import com.project.spring_rest_project.entity.Customer;
import com.project.spring_rest_project.entity.Event;
import com.project.spring_rest_project.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/customer/{customerId}/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public Iterable<Customer> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping
    public Event addEvent(@PathVariable Long customerId, @RequestBody Event event) {
        return eventService.addEvent(customerId, event);
    }

}
