package com.example.demo.Service;

import com.example.demo.Entity.Activity;
import com.example.demo.Entity.Volunteer;
import com.example.demo.Repository.ActivityRepository;
import com.example.demo.Repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private VolunteerService volunteerService;

    //read all
    public Iterable<Activity> getAll(){
        return activityRepository.findAll();
    }

    //create
    public Activity createActivity(Activity activity, int voluntId){
        Volunteer volunteer = volunteerService.findById(voluntId);
//        activity.setVolunteer(volunteer);
        volunteer.getActivityList().add(activity);
        volunteerService.updateVolunteer(volunteer, voluntId);
        return activity;
    }

    //read one
    public Activity getById(int id){
        Optional<Activity> optionalActivity = activityRepository.findById(id);
        return optionalActivity.orElseThrow(() -> new RuntimeException("Activity not found on database"));
    }

    //update
    public Activity updateActivity(int id, Activity activity){
        Optional<Activity> optionalActivity = activityRepository.findById(id);

        if (optionalActivity.isPresent()){
            activity.setActivityId(id);
            return activityRepository.save(activity);
        }
        return optionalActivity.orElseThrow(() -> new RuntimeException("Activity not found on database"));
    }

    //delete
    public void deleteActivity(int id){
        activityRepository.deleteById(id);
    }


}
