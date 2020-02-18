package com.example.controller.person;

import com.example.model.Person;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

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
    public void updatePerson(@RequestBody Person person, @PathVariable int id){
        personService.updatePerson(person, id);
    }

}
