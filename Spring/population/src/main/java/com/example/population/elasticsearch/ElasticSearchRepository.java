package com.example.population.elasticsearch;

import com.example.population.domain.Camera;
import com.example.population.domain.Member;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface ElasticSearchRepository extends ElasticsearchRepository<Camera, Long> {
}
