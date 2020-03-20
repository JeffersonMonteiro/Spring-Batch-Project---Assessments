package com.example.demo.Service;

import com.example.demo.Controller.ActivityController;
import com.example.demo.Entity.Activity;
import com.example.demo.Entity.Volunteer;
import com.example.demo.Repository.ActivityRepository;
import com.example.demo.Repository.VolunteerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ActivityServiceTest {

    private Activity activity1;
    private Activity activity2;
    private List<Activity> activityList;

    private Volunteer volunteer;

    @Mock
    ActivityRepository activityRepository;

    @Mock
    VolunteerRepository volunteerRepository;

    @Mock
    VolunteerService volunteerService = new VolunteerService(volunteerRepository);

    @InjectMocks
    ActivityService activityService = new ActivityService(activityRepository, volunteerService);


    @Before
    public void setUp(){
        this.activity1 = new Activity(1, "CC", 1509, "Caximba");
        this.activity2 = new Activity(1, "ECO", 1706, "Parolin");
        this.volunteer = new Volunteer(1,"Leia Skywalker", 21, 15, 5);
        activityList = new ArrayList<>();
        volunteerService.createVolunteer(volunteer);
        volunteer.setActivityList(activityList);
    }

    @Test
    public void getAll_ShouldReturnAllActivities_WhenExist() {
        //given
        volunteer.getActivityList().add(activity1);
        volunteer.getActivityList().add(activity2);
        volunteerService.updateVolunteer(volunteer, volunteer.getId());
        //when
        when(activityRepository.findAll()).thenReturn(activityList);
        //then
        assertEquals(activityList, activityService.getAll());
    }

    @Test
    public void createActivity_ShouldReturnCreatedActivity_WhenCreated() {
        //given
        volunteer.getActivityList().add(activity1);
        volunteerService.updateVolunteer(volunteer, volunteer.getId());

        //when
        when(volunteerService.updateVolunteer(volunteer, volunteer.getId())).thenReturn(volunteer);
        //then
        assertEquals(activity1, activityService.createActivity(activity1, volunteer.getId()));
    }

//    @Test
//    public void getById() {
//    }
//
//    @Test
//    public void updateActivity() {
//    }
//
//    @Test
//    public void deleteActivity() {
//    }
}