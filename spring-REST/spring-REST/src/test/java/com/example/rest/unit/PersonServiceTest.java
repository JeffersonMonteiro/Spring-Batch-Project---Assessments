package com.example.rest.unit;

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
public class PersonServiceTest {

    Person person1;
    Person person2;
    Person person3;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    PersonService personService = new PersonService(personRepository);

    @Before
    public void setUp() {
        person1 = new Person("Joao", 21, 1);
        person2 = new Person("Vitor", 22, 2);
        person3 = null;
    }

    @Test
    public void GetAllTest(){
        List<Person> people = new ArrayList<>();
        people.add(person1);
        people.add(person2);
        when(personRepository.findAll()).thenReturn(people);
        Assert.assertEquals(people, personService.getAll());
    }

    @Test
    public void createPersonTest(){
        when(personRepository.save(person1)).thenReturn(person1);
        Assert.assertEquals(person1, personService.createPerson(person1));
    }

    @Test
    public void getByIdTest(){
        when(personRepository.findById(person1.getId())).thenReturn(Optional.of(person1));
        Assert.assertEquals(person1, personService.getById(person1.getId()));
    }

    @Test
    public void removePersonTest(){
        personRepository.deleteById(person1.getId());
        verify(personRepository).deleteById(person1.getId());
    }

    @Test
    public void updatePersonTest(){
        when(personRepository.save(person1)).thenReturn(person1);
        Assert.assertEquals(person1 , personService.updatePerson(person1, person1.getId()));
    }

    @Test
    public void GetAllNullTest(){
        when(personRepository.findAll()).thenReturn(null);
        Assert.assertEquals(null, personService.getAll());
    }

}