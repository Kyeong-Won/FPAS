package com.example.population.repository;

import com.example.population.domain.Plugin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PluginRepository extends JpaRepository<Plugin, String>, JpaSpecificationExecutor<Plugin> {

}