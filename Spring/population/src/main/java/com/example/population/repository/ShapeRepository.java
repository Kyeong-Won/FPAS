package com.example.population.repository;

import com.example.population.domain.Member;
import com.example.population.domain.Shape;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShapeRepository extends JpaRepository<Shape, Long> {
    Optional<Shape> findByName(String name);
}
