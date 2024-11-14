package com.example.mapasearch.controller;

import com.example.mapasearch.dto.RequestDto;
import com.example.mapasearch.repository.PlaceRepository;
import com.example.mapasearch.service.ElasticSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/elastic")
public class ElasticSearchController {

    private final ElasticSearchService elasticSearchService;
    private final PlaceRepository placeRepository;
    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchController.class);

    public ElasticSearchController(ElasticSearchService elasticSearchService, PlaceRepository placeRepository) {
        this.elasticSearchService = elasticSearchService;
        this.placeRepository = placeRepository;
    }

    @PostMapping("/searchF")
    public List<Map<String, Object>>  searchByFolio(@RequestBody RequestDto requestDto) {
        boolean validacion = false;
        int folio = 0;
        try{
            var result = elasticSearchService.buscarPorFolio("folios", requestDto.getFolio(), Object.class);
            logger.info(result.toString());
            if (result != null) {
                validacion = true;
                if(validacion =! false){
                    folio = Integer.parseInt(requestDto.getFolio());
                    logger.info("Folio: " + placeRepository.findSurvey(folio));
                    return placeRepository.findSurvey(folio);
                }
            }else {
                logger.error("El folio no tiene nada");
            }
        } catch (Exception e) {
            logger.error("Este folio no es aplicable al programa.");
        }

        return List.of();
    }

}
