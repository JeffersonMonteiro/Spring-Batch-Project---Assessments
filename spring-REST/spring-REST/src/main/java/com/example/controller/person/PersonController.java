package com.example.controller.person;

import com.example.model.Person;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping
    public Iterable<Person> getAll(){
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable int id){
        return  personService.getById(id);
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person){
        return personService.createPerson(person);
    }

    @DeleteMapping("/{id}")
    public void removePerson(@PathVariable int id){
        personService.removePerson(id);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@RequestBody Person person, @PathVariable int id){
            return personService.updatePerson(person, id);
    }

    @GetMapping("/name/{name}")
    public Iterable<Person> findByName(@PathVariable String name){
        return personService.findByName(name);
    }

}
