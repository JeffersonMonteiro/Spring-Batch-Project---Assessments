package com.example.demo.Controller;

import com.example.demo.Entity.Activity;
import com.example.demo.Entity.Volunteer;
import com.example.demo.Exception.APIException;
import com.example.demo.Service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Autowired
    VolunteerController volunteerController;

    @GetMapping("/get")
    public Iterable<Activity> getAll(){
        return activityService.getAll();
    }

    @PostMapping("/add/{voluntId}")
    public Activity createActivity(@RequestBody Activity activity, @PathVariable("voluntId") int id) throws APIException{
        Volunteer volunteer = volunteerController.findById(id);
        activity.setVolunteer(volunteer);
        Activity createdActivity = activityService.createActivity(activity);
        volunteerController.addToActivityList(createdActivity, id);
        return createdActivity;
    }

    @GetMapping("/get/{id}")
    public Activity getById(@PathVariable int id){
        return activityService.getById(id);

    }

    @PutMapping("/update/{id}")
    public Activity updateActivity(@RequestBody Activity activity, @PathVariable("id") int id){
        return activityService.updateActivity(id, activity);
    }

    @DeleteMapping("/delete/{id}/{voluntId}")
    public void deleteActivity(@PathVariable("id") int id, @PathVariable("voluntId") int voluntId){
        activityService.deleteActivity(id, voluntId);
    }

}
