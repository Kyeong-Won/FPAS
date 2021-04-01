package com.example.population.repository;

import com.example.population.domain.HelpTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HelpTopicRepository extends JpaRepository<HelpTopic, Long>, JpaSpecificationExecutor<HelpTopic> {

}