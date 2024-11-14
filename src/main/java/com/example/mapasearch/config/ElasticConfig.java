package com.example.mapasearch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;


@Configuration
public class ElasticConfig {

    @Bean
    public ElasticsearchClient elasticsearchClient(){
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 9200, "http")
        ).build();

        RestClientTransport transport = new RestClientTransport(restClient ,
                new co.elastic.clients.json.jackson.JacksonJsonpMapper()
        );

        return new ElasticsearchClient(transport);
    }

}
