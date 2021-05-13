package com.example.population.repository;

import com.example.population.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<Files, Long> {
    Optional<Files> findById(int id);
}
