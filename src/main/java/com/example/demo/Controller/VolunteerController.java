package com.example.demo.Controller;

import com.example.demo.Entity.Volunteer;
import com.example.demo.Service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/volunteer")
public class VolunteerController {

    @Autowired
    VolunteerService volunteerService;

    @GetMapping("/get")
    public Iterable<Volunteer> getAll(){
        return volunteerService.getAll();
    }

    @PostMapping("/add")
    public Volunteer createVolunteer(@RequestBody Volunteer volunteer){
        return volunteerService.createVolunteer(volunteer);
    }

    @GetMapping("/get/{id}")
    public Volunteer findById(@PathVariable int id){
       return volunteerService.findById(id);
    }

    @PutMapping("/update/{id}")
    public  Volunteer updateVolunteer(@RequestBody Volunteer volunteer, @PathVariable int id){
        return volunteerService.updateVolunteer(volunteer, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVolunteer(@PathVariable int id){
        volunteerService.deleteVolunteer(id);
    }
}