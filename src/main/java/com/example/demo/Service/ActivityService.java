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

    public  ActivityService(ActivityRepository activityRepository, VolunteerService volunteerService){
        this.activityRepository = activityRepository;
        this.volunteerService = volunteerService;
    }

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
//        optionalActivity.orElseThrow(() -> new RuntimeException("Activity not found on database"));
        return activity;
    }

    //delete
    public void deleteActivity(int id, int volunteerId) {
        Optional<Activity> optionalActivity = activityRepository.findById(id);

        optionalActivity.ifPresent(activity -> {
           volunteerService.deleteActivityFromVolunteer(activity, volunteerId);
           activityRepository.deleteById(id);
        });

    }

}
