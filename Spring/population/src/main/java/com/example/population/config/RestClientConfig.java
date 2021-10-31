package com.example.population.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories("com.example.population.repository")
public class RestClientConfig extends AbstractElasticsearchConfiguration {

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                //.connectedTo("https://search-domain-fpas-jlxb5qa2bhmmfdfznkxvvztj54.ap-northeast-2.es.amazonaws.com:9200")
                .build();
//


        return RestClients.create(clientConfiguration).rest();
        //return new RestHighLevelClient(RestClient.builder(HttpHost.create("https://search-domain-fpas-jlxb5qa2bhmmfdfznkxvvztj54.ap-northeast-2.es.amazonaws.com")));
    }
}
