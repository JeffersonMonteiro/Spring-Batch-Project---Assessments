package com.example.demo.controller;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping
    public  Iterable<Event> getAll(){
        return eventService.getAll();
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event){
        return eventService.createEvent(event);
    }

    @GetMapping("/{id}")
    public Event findVolunteerById(@PathVariable int id){
        return eventService.findEventById(id);
    }

    @PutMapping
    public Event updateEvent (@RequestBody Event event){
        return eventService.updateEvent(event);
    }

    @DeleteMapping
    public void deleteEvent(int id){
        eventService.deleteEventById(id);
    }


}


