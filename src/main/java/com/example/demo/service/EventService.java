package com.example.demo.service;

import com.example.demo.entity.Event;
import com.example.demo.entity.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventsService {

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Volunteer volunteer;

    public void addEvent(Event event){
        
    }


}
