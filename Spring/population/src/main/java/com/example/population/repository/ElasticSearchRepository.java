package com.example.population.repository;

import com.example.population.domain.Camera;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticSearchRepository extends ElasticsearchRepository<Camera, Long> {

}
