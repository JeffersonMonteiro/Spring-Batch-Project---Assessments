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

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class VolunteerControllerTest {

    private Volunteer volunteer1;
    private Volunteer volunteer2;

    @Mock
    VolunteerService volunteerService;

    @InjectMocks
    VolunteerController volunteerController = new VolunteerController(volunteerService);

    @Before
    public void setUp(){
        volunteer1 = new Volunteer(1,"Leia Skywalker", 21, 15, 5);
        volunteer2 = new Volunteer(2, "Padmé Amidala", 37, 8, 2);
    }

    @Test
    public void getAll_GivenListWithTwoVolunteers_ShouldReturnListWithTwoVolunteers() throws Exception {
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
    public void createVolunteer_WhenNewVolunteerIsAdded_ShouldReturnCreatedVolunteer() throws Exception {
        //when
        when(volunteerService.createVolunteer(volunteer1)).thenReturn(volunteer1);

        //then
        Assert.assertEquals(volunteer1, volunteerController.createVolunteer(volunteer1));
    }

    @Test
    public void findById_whenIdExists_shouldReturnVolunteer() {
        //when
        when(volunteerService.findById(volunteer1.getId())).thenReturn(volunteer1);
        //then
        Assert.assertEquals(volunteer1, volunteerController.findById(volunteer1.getId()));
    }

    @Test
    public void updateVolunteer_whenVolunteerIsUpdates_ShouldReturnUpdatedVolunteer() {
        //when
        when(volunteerService.updateVolunteer(volunteer1, volunteer1.getId())).thenReturn(volunteer1);

        //then
        Assert.assertEquals(volunteer1, volunteerController.updateVolunteer(volunteer1, volunteer1.getId()));
    }

    @Test
    public void deleteVolunteer_WhenVolunteersIsDeleted_shouldReturnNull() {
        //when
        volunteerController.deleteVolunteer(volunteer1.getId());
        //then
        verify(volunteerService).deleteVolunteer(volunteer1.getId());
    }
}