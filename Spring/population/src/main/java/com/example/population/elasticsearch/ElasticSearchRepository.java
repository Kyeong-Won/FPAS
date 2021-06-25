package com.example.population.elasticsearch;

import com.example.population.domain.Camera;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticSearchRepository extends ElasticsearchRepository<Camera, Long> {

}
