package com.example.demo.Service;

import com.example.demo.Entity.Activity;
import com.example.demo.Entity.Volunteer;
import com.example.demo.Repository.ActivityRepository;
import com.example.demo.Repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private ActivityRepository activityRepository;

    // read all
    public Iterable<Volunteer> getAll(){
        return volunteerRepository.findAll();
    }

    //create
    public Volunteer createVolunteer(Volunteer volunteer){
        return volunteerRepository.save(volunteer);
    }

    //read one
    public Volunteer findById(int id){
        Optional<Volunteer> optionalVolunteer = volunteerRepository.findById(id);
        return optionalVolunteer.orElseThrow(() -> new RuntimeException("Volunteer does not exist on database"));
    }
    //update
    public  Volunteer updateVolunteer(Volunteer volunteer, Integer id){
        Optional<Volunteer> optionalVolunteer = volunteerRepository.findById(id);

        if (optionalVolunteer.isPresent()){
                return volunteerRepository.save(volunteer);
        }
//        return optionalVolunteer.orElseThrow(() -> new RuntimeException("Volunteer id not found"));
        return volunteer;
    }

    //delete
    public void deleteVolunteer(int id){
        volunteerRepository.deleteById(id);
    }

    public  void deleteActivityFromVolunteer(Activity activity, int volunteerId){
        Volunteer volunteer = findById(volunteerId);
        volunteer.getActivityList().remove(activity);
        updateVolunteer(volunteer, volunteerId);
    }

    //constructor

    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }
}
