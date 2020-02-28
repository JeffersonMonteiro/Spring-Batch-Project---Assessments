package com.example.demo.Repository;

import com.example.demo.Entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {
}
