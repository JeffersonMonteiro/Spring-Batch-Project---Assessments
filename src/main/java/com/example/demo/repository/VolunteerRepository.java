package com.example.demo.repository;

import com.example.demo.entity.Volunteer;
import org.springframework.data.repository.CrudRepository;

public interface VolunteerRepository  extends CrudRepository<Volunteer, Integer>{
}
