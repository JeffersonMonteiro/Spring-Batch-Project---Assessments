package com.example.demo.service;

import com.example.demo.entity.Volunteer;
import com.example.demo.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    public Volunteer createVolunteer (Volunteer volunteer){
        return volunteerRepository.save(volunteer);
    }


    public Iterable<Volunteer> getAll(){
        return volunteerRepository.findAll();
    }

    public Volunteer findVoluntById(int id){
        Optional<Volunteer> optionalVolunteer = volunteerRepository.findById(id);
        return  optionalVolunteer.orElseThrow(() -> new RuntimeException("Volunteer not found"));
    }

    public void removeVoluntById(int id){
        volunteerRepository.deleteById(id);
    }

    public void updateVolunteer(Volunteer volunteer){
        volunteerRepository.save(volunteer);
    }
}
