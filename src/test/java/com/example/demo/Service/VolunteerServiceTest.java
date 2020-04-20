package com.example.demo.Service;

import com.example.demo.Entity.Volunteer;
import com.example.demo.Repository.VolunteerRepository;
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

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class VolunteerServiceTest {

    private Volunteer volunteer1;
    private Volunteer volunteer2;

    @Mock
    private VolunteerRepository volunteerRepository;

    @InjectMocks
    VolunteerService volunteerService = new VolunteerService(volunteerRepository);

    @Before
    public void setUp(){
        volunteer1 = new Volunteer(1,"Leia Skywalker", 21, 15, 5);
        volunteer2 = new Volunteer(2,"Padmé Amidala", 37, 8, 2);

    }

    @Test
    public void getAll_ListWihTwoVolunteers_ShouldReturnListOfTwoVolunteers() {
        //given
        List<Volunteer> volunteersList = new ArrayList<>();
        volunteersList.add(volunteer1);
        volunteersList.add(volunteer2);

        //when
        when(volunteerRepository.findAll()).thenReturn(volunteersList);

        //then
        Assert.assertEquals(volunteersList, volunteerService.getAll());
    }

    @Test
    public void getAll_GivenNoVolunteers_ShouldReturnNull(){
        //when
        when(volunteerRepository.findAll()).thenReturn(null);

        //then
        Assert.assertEquals(null, volunteerService.getAll());
    }

    @Test
    public void createVolunteer_WhenVolunteerIsCreated_shouldReturnCreatedVolunteer() throws Exception {
        //when
        when(volunteerRepository.save(volunteer1)).thenReturn(volunteer1);
        //then
        Assert.assertEquals(volunteer1, volunteerService.createVolunteer(volunteer1));
    }

    @Test
    public void createVolunteer_WhenNullVolunteerCreated_ShouldReturnNull() throws Exception {
        //when
        when(volunteerRepository.save(null)).thenReturn(null);
        //then
        Assert.assertEquals(null, volunteerService.createVolunteer(null));
    }

    @Test
    public void findById_WhenVolunteerIdExists_ShouldReturnCorrectVolunteer() {
        //when
        when(volunteerRepository.findById(volunteer1.getId())).thenReturn(Optional.of(volunteer1));
        //then
        Assert.assertEquals(volunteer1, volunteerService.findById(volunteer1.getId()));
    }

    @Test
    public void updateVolunteer_WhenVolunteerExists_ShouldReturnUpdatedVolunteer() {
        //when
        when(volunteerRepository.save(volunteer1)).thenReturn(volunteer1);
        //then
        Assert.assertEquals(volunteer1, volunteerService.updateVolunteer(volunteer1, volunteer1.getId()));
    }

    @Test
    public void deleteVolunteer_WhenIdExists_ShouldDeleteAndReturnNull() {
        //when
        volunteerService.deleteVolunteer(volunteer1.getId());
        //then
        verify(volunteerRepository).deleteById(volunteer1.getId());
    }
}