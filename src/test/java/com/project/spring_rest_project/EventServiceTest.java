package com.project.spring_rest_project;

import com.project.spring_rest_project.entity.Customer;
import com.project.spring_rest_project.entity.Event;
import com.project.spring_rest_project.repository.EventRepository;
import com.project.spring_rest_project.service.CustomerService;
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
public class EventServiceTest {

    private Customer customer;
    private Event event;

    @Mock
    CustomerService customerService;

    @Mock
    EventRepository eventRepository;

    @InjectMocks
    EventService eventService;

    @Before
    public void setUp() {
        List<Event> events = new ArrayList<>();
        customer = new Customer(1, "Ana", "ana@mail.com", "9898-9898");
        customer.setEvents(events);
        event = new Event((long) 2, "Aniversário da Ana", "Casa do João", "20-08-2020");

    }

    @Test
    public void getAllEvents() {
        List<Customer> events = new ArrayList<>();
        events.add(customer);
        when(eventService.getAllEvents()).thenReturn(events);
        Assert.assertEquals(events, eventService.getAllEvents());
    }

    @Test
    public void createEventsTest() {
        List<Event> events = new ArrayList<>();
        customer.setEvents(events);
        when(customerService.findById(customer.getCustomerId())).thenReturn(customer);
        when(customerService.updateCustomer(customer)).thenReturn(customer);
        Event eventSaved = eventService.addEvent(customer.getCustomerId(), event);
        Assert.assertTrue(eventSaved.getCode() == 2);
    }







}
