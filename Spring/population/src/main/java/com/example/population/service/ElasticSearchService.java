package com.example.population.service;

import com.example.population.domain.Camera;
import com.example.population.elasticsearch.ElasticSearchRepository;
import lombok.RequiredArgsConstructor;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ElasticSearchService {
    private final ElasticsearchOperations elasticsearchOperations;
    private final ElasticSearchRepository elasticSearchRepository;

    public Long save(Camera camera){
        return elasticSearchRepository.save(camera).getId();
    }

    public int count(String place, Date from_str, Date to_str) throws ParseException {
//        String from_str = "2021-06-23 18:25:40.728";
        String from = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(from_str);

//        String to_str = "2021-06-23 21:25:40.728";
        String to = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(to_str);

        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.rangeQuery("date").gte(from).lte(to).format("strict_date_optional_time"))
                .must(QueryBuilders.matchQuery("place", place));
        SumAggregationBuilder sumBuilder = AggregationBuilders.sum("sum_value").field("count");
        System.out.println(queryBuilder);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .addAggregation(sumBuilder)
                .build();

        SearchHits<Camera> productHits =
                elasticsearchOperations
                        .search(searchQuery,
                                Camera.class,
                                IndexCoordinates.of("info"));

        ParsedSum a = productHits.getAggregations().get("sum_value");
        int sumCount = (int)a.getValue();
//        System.out.println("a.getValue() = " + sumCount);
        return sumCount;
    }
}


