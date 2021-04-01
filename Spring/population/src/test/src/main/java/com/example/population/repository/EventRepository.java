package com.example.population.repository;

import com.example.population.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EventRepository extends JpaRepository<Event, String>, JpaSpecificationExecutor<Event> {

}