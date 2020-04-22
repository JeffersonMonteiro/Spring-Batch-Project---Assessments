package com.project.spring_rest_project;


import com.project.spring_rest_project.controller.EventController;
import com.project.spring_rest_project.entity.Customer;
import com.project.spring_rest_project.entity.Event;
import com.project.spring_rest_project.service.EventService;
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
public class EventControllerTest {

    private Customer customer;

    private Event event;

    @Mock
    EventService eventService;

    @InjectMocks
    EventController eventController;

    @Before
    public void setUp() {
        event = new Event((long) 2, "Aniversario da Ana", "Casa do João", "20-08-2020");
        customer = new Customer((long) 1, "Ana", "ana@mail.com", "9898-9898");
    }

    @Test
    public void getAllEvents() {
        List<Customer> events = new ArrayList<>();
        events.add(customer);
        when(eventService.getAllEvents()).thenReturn(events);
        Assert.assertEquals(events, eventController.getAllEvents());
    }

    @Test
    public void addEvent() {
        when(eventService.addEvent(customer.getCustomerId(), event)).thenReturn(event);
        Assert.assertEquals(event, eventController.addEvent(event, customer.getCustomerId()));
    }



}
