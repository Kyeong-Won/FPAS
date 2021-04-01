package com.example.population.repository;

import com.example.population.domain.HelpCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HelpCategoryRepository extends JpaRepository<HelpCategory, Integer>, JpaSpecificationExecutor<HelpCategory> {

}