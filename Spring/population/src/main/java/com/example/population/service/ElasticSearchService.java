package com.example.population.service;

import com.example.population.domain.Camera;
import com.example.population.elasticsearch.ElasticSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ElasticSearchService {

    private final ElasticSearchRepository elasticSearchRepository;

    public Long save(Camera camera){
        return elasticSearchRepository.save(camera).getId();
    }
}
