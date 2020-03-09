package com.example.demo.repository;

import com.example.demo.entity.Event;
import com.example.demo.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
