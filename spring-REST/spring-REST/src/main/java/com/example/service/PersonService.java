package com.example.service;


import com.example.model.Person;
import com.example.model.Product;
import com.example.repository.PersonRepository;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Iterable<Person> getAll(){

        return personRepository.findAll();
    }

    public Person createPerson(Person person){

        return personRepository.save(person);
    }

    public Person getById(int id){

        Optional<Person> optionalPerson = personRepository.findById(id);
        return optionalPerson.orElseThrow(() -> new RuntimeException("Person does not exist"));
    }

    public void removePerson(int id){

        personRepository.deleteById(id);
    }

    public Person updatePerson(Person person, int id){

        Optional<Person> OptPerson = personRepository.findById(id);

        if(OptPerson.isPresent()){
            person.setId(id);
            return personRepository.save(person);
        }
        return personRepository.save(person);
    }

    public Iterable<Person> findByName(String name){
        Iterable<Person> persons = this.getAll();
        List<Person> personsFound = new ArrayList<>();
        for (Person person : persons){
            if(person.getName().contains(name)){
                personsFound.add(person);
            }
        }
        return personsFound;
    }
}
