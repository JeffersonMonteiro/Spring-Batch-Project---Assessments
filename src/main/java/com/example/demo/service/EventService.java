package com.example.demo.service;

import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EventService {

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    EventRepository eventRepository;

    public Event createEvent(Event event){
        return eventRepository.save(event);
    }

    public Iterable<Event> getAll(){
        return eventRepository.findAll();
    }

    public Event findEventById(int id){
        Optional<Event> optEvent = eventRepository.findById(id);
        return optEvent.orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public Event updateEvent(Event event){
       Optional<Event> optionalEvent = eventRepository.findById(event.getId());
       if (optionalEvent.isPresent()){
           return eventRepository.save(event);
       }
        return optionalEvent.orElseThrow(() -> new RuntimeException("Product not found on database"));
    }

    public void deleteEventById (int id){
        eventRepository.deleteById(id);
    }

}
