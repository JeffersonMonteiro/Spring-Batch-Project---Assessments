package com.project.spring_rest_project.repository;

import com.project.spring_rest_project.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Customer, Long> {
}
