package com.example.population.service;

import com.example.population.domain.Camera;
import com.example.population.elasticsearch.ElasticSearchRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.junit.jupiter.api.Assertions.*;

class ElasticSearchServiceTest {


    @Test
    void save() {
    }

    @Test
    void count() {
    }

    @Test
    void delete() {

    }
}