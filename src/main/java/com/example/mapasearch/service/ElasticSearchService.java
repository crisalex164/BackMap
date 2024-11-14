package com.example.mapasearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ElasticSearchService {

    private final ElasticsearchClient client;

    public ElasticSearchService(ElasticsearchClient client) {
        this.client = client;
    }

    public <T> T buscarPorFolio(String index, String folio, Class<T> clazz) throws IOException {
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index(index)
                .query(q -> q
                        .term(t -> t
                                .field("folio")
                                .value(folio)
                        )
                )
        );

        SearchResponse<T> response = client.search(searchRequest, clazz);
        List<Hit<T>> hits = response.hits().hits();

        return hits.isEmpty() ? null : hits.get(0).source();
    }

}
