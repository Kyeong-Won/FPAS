package com.example.population.repository;

import com.example.population.domain.Shape;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShapeRepository extends JpaRepository<Shape, Long> {
}
