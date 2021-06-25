package com.example.population.repository;

import com.example.population.domain.Camera;
import com.example.population.domain.Member;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface ElasticSearchRepository extends ElasticsearchRepository<Camera, Long> {
}
