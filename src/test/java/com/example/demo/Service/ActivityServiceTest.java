package com.example.demo.Service;

import com.example.demo.Entity.Activity;
import com.example.demo.Entity.Volunteer;
import com.example.demo.Repository.ActivityRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
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
    VolunteerService volunteerService;

    @InjectMocks
    ActivityService activityService;


    @Before
    public void setUp() {
        this.activity1 = new Activity(1, "CC", 1509, "Caximba");
        this.activity2 = new Activity(2, "ECO", 1706, "Parolin");
        this.volunteer = new Volunteer(1, "Leia Skywalker", 21, 15, 5);
        this.activityList = new ArrayList<>();
        volunteerService.createVolunteer(volunteer);
        volunteer.setActivityList(activityList);
    }

    @Test
    public void getAll_givenListWithTwoActivities_shouldReturnListWithTwoActivities() {
        //given
        volunteer.getActivityList().add(activity1);
        volunteer.getActivityList().add(activity2);
        //when
        when(activityRepository.findAll()).thenReturn(activityList);
        //then
        assertEquals(activityList, activityService.getAll());
    }

    @Test
    public void createActivity_givenActivityToCreate_ShouldReturnCreatedActivity() {
        //given
        volunteer.getActivityList().add(activity1);
        //when
        when(volunteerService.findById(volunteer.getId())).thenReturn(volunteer);
        when(volunteerService.updateVolunteer(volunteer, volunteer.getId())).thenReturn(volunteer);
        //then
        assertEquals(activity1, activityService.createActivity(activity1));
    }

    @Test
    public void getById_WhenActivityWithSearchedId_ShouldReturnActivityWithSearchedId() {
        //when
        when(activityRepository.findById(activity1.getId())).thenReturn(Optional.of(activity1));
        //then
        Assert.assertEquals(activity1, activityService.getById(activity1.getId()));
    }

    @Test
    public void updateActivity_WhenActivityIsUpdated_ShoulfReturnUpdatedActivity() {
        //when
        when(activityRepository.save(activity1)).thenReturn(activity1);
        //then
        Assert.assertEquals(activity1, activityService.updateActivity(activity2.getId(), activity1));
    }

    @Test
    public void deleteActivity_WhenVolunteerIdFound_WhenActivityIsFound_ShouldDeleteActivityAndReturnNull() {
        //when
        when(volunteerService.findById(volunteer.getId())).thenReturn(volunteer);
        when(activityRepository.findById(activity1.getId())).thenReturn(Optional.of(activity1));

        activityService.deleteActivity(activity1.getId(), volunteer.getId());
        //then
        verify(volunteerService).deleteActivityFromVolunteer(activity1, volunteer.getId());
    }
}