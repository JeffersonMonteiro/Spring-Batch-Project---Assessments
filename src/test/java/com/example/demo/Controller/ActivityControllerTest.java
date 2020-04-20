package com.example.demo.Controller;

import com.example.demo.Entity.Activity;
import com.example.demo.Entity.Volunteer;
import com.example.demo.Service.ActivityService;
import com.example.demo.Service.VolunteerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ActivityControllerTest {

    private Activity activity1;
    private Activity activity2;
    private List<Activity> activityList;
    private Volunteer volunteer;

    @Mock
    ActivityService activityService;
    @Mock
    VolunteerService volunteerService;
    @InjectMocks
    ActivityController activityController;

    @Before
    public void setUp() throws Exception {
        this.activity1 = new Activity(1, "CC", "1509", "Caximba");
        this.activity2 = new Activity(2, "ECO", "1706", "Parolin");
        this.volunteer = new Volunteer(1, "Leia Skywalker", 21, 15, 5);
        this.activityList = new ArrayList<>();
        volunteerService.createVolunteer(volunteer);
        volunteer.setActivityList(activityList);
    }

    @Test
    public void getAll_shouldReturnAllActivities() {
        //given
        volunteer.getActivityList().add(activity1);
        volunteer.getActivityList().add(activity2);
        volunteerService.updateVolunteer(volunteer, volunteer.getId());

        //when
        when(activityService.getAll()).thenReturn(activityList);

        //then
        Assert.assertEquals(activityList, activityController.getAll());

    }

    @Test
    public void createActivity_ShouldReturnActivity_WhenCreated() {
        //given
        volunteer.getActivityList().add(activity1);
        volunteerService.updateVolunteer(volunteer, volunteer.getId());
        //
        //when
        when(activityService.createActivity(activity1)).thenReturn(activity1);
        //then
        Assert.assertEquals(activity1, activityController.createActivity(activity1, volunteer.getId()));
    }

    @Test
    public void getById() {
        //when
        when(activityService.getById(activity1.getId())).thenReturn(activity1);
        //then
        Assert.assertEquals(activity1, activityController.getById(activity1.getId()));
    }

    @Test
    public void updateActivity() {
        //when
        when(activityService.updateActivity(activity1.getId(), activity1)).thenReturn(activity1);
        //then
        Assert.assertEquals(activity1, activityController.updateActivity(activity1, activity1.getId()));
    }

    @Test
    public void deleteActivity() {
        //when
        when(volunteerService.findById(volunteer.getId())).thenReturn(volunteer);
        when(activityService.getById(activity1.getId())).thenReturn(activity1);

        activityController.deleteActivity(activity1.getId(), volunteer.getId());
        //then
        verify(activityService).deleteActivity(volunteer.getId(), activity1.getId());
    }
}