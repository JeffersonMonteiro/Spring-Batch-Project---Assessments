package com.example.demo.repository;

import com.example.demo.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository  extends JpaRepository<Volunteer, Integer> {
}
