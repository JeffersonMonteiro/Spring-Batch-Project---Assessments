package com.example.demo.Controller;

import com.example.demo.Entity.Volunteer;
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
import java.util.Optional;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class VolunteerControllerTest {

    private Volunteer volunteer1;
    private Volunteer volunteer2;

    @Mock
    VolunteerService volunteerService;

    @InjectMocks
    VolunteerController volunteerController;

    @Before
    public void setUp(){
        Volunteer volunteer1 = new Volunteer(1,"Leia Skywalker", 21, 15, 5);
        Volunteer volunteer2 =new Volunteer(2, "Padmé Amidala", 37, 8, 2);
    }

    @Test
    public void getAll_ShouldReturn_AllVolunteers() throws Exception {
        //given
        List<Volunteer> volunteersList = new ArrayList<>();
        volunteersList.add(volunteer1);
        volunteersList.add(volunteer2);

        //when
        when(volunteerService.getAll()).thenReturn(volunteersList);

        //then
        Assert.assertEquals(volunteersList, volunteerController.getAll());
      }

    @Test
    public void createVolunteer_ShouldReturnVolunteer_WhenCreated() {
        //when
        when(volunteerService.createVolunteer(volunteer1)).thenReturn(volunteer1);

        //then
        Assert.assertEquals(volunteer1, volunteerController.createVolunteer(volunteer1));
    }

    @Test
    public void findById_shouldReturnVolunteer_whenIdExists() {
        //when
        when(volunteerService.findById(volunteer1.getId())).thenReturn(volunteer1);
        //then
        Assert.assertEquals(volunteer1, volunteerController.findById(volunteer1.getId()));
    }

//    @Test
//    void updateVolunteer() {
//    }
//
//    @Test
//    void deleteVolunteer() {
//    }
}