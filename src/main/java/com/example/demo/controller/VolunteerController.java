package com.example.demo.controller;

import com.example.demo.entity.Volunteer;
import com.example.demo.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @GetMapping
    public Iterable<Volunteer> getAll(){
        return volunteerService.getAll();
    }

    @GetMapping("/{id}")
    public Volunteer findVolunteerById(@PathVariable int id){
        return volunteerService.findVoluntById(id);
    }

    @PostMapping
    public Volunteer createVolunteer(@RequestBody Volunteer volunteer){
        return volunteerService.createVolunteer(volunteer);
    }

    @DeleteMapping("/{id}")
    public void removeVolunteer (@PathVariable int id){
        volunteerService.removeVoluntById(id);
    }

    @PutMapping
    public  void updateVolunteer(@RequestBody Volunteer volunteer){
        volunteerService.updateVolunteer(volunteer);
    }

}
