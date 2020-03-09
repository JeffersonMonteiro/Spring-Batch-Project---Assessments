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

    @GetMapping ("/get")
    public  Iterable<Event> getAll(){
        return eventService.getAll();
    }

    @PostMapping("/add/{id_volunt}")
    public Event createEvent(@RequestBody Event event, @PathVariable int id_volunt){
        return eventService.createEvent(event, id_volunt);
    }

    @GetMapping("/get/{id}")
    public Event findVolunteerById(@PathVariable int id){
        return eventService.findEventById(id);
    }

    @PutMapping("/update/{event_id}")
    public Event updateEvent (@RequestBody Event event, @PathVariable int event_id){
        return eventService.updateEvent(event, event_id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEvent(int id){
        eventService.deleteEventById(id);
    }
}


