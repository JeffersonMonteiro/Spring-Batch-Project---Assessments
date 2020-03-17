package com.example.rest;

import com.example.controller.person.PersonController;
import com.example.model.Person;
import com.example.repository.PersonRepository;
import com.example.service.PersonService;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class PersonControllerTest {

    Person person1;
    Person person2;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    PersonService personService = new PersonService(personRepository);

    @InjectMocks
    PersonController personController = new PersonController(personService);

    @Before
    public void setUp() {
        person1 = new Person("Joao", 21, 1);
        person2 = new Person("Vitor", 22, 2);
    }

    @Test
    public void getAllTest(){
        List<Person> people = new ArrayList<>();
        people.add(person1);
        people.add(person2);
        when(personService.getAll()).thenReturn(people);
        Assert.assertEquals(people, personController.getAll());
    }

    @Test
    public void createPersonTest(){
        when(personService.createPerson(person1)).thenReturn(person1);
        Assert.assertEquals(person1, personController.createPerson(person1));
    }

    @Test
    public void getByIdTest(){
        when(personService.getById(person1.getId())).thenReturn(person1);
        Assert.assertEquals(person1, personController.getById(person1.getId()));
    }

    @Test
    public void removePersonTest(){
        personController.removePerson(person1.getId());
        verify(personRepository).deleteById(person1.getId());
    }

    @Test
    public void updatePersonTest(){
        when(personService.updatePerson(person1, person1.getId())).thenReturn(person1);
        Assert.assertEquals(person1, personController.updatePerson(person1, person1.getId()));
    }

}