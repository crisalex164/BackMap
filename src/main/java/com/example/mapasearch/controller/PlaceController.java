package com.example.mapasearch.controller;



import com.example.mapasearch.dto.PlaceDto;
import com.example.mapasearch.dto.SurveyDto;
import com.example.mapasearch.service.ElasticSearchService;
import com.example.mapasearch.dto.RequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mapasearch.repository.PlaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/place")
@CrossOrigin(origins = "*")
public class PlaceController {

    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);
    @Autowired
    private ElasticSearchService elasticSearchService;

    /*
    @PostMapping("/buscar")
    public List<Map<String, Object>> getNearest(@RequestBody PlaceDto placeDto) {
        long lat = (long) placeDto.getLat();
        long lng = (long) placeDto.getLng();
        List<Map<String, Object>> results = placeRepository.findNearest(lng, lat);
        return results.stream().map(result -> Map.of("survey", result.get("out_survey_user_folio_id"), "geom", result.get("out_geom"))).collect(Collectors.toList());
    }
*/
    @PostMapping("/fol")
    public List<Map<String, Object>> findFolio(@RequestBody SurveyDto surveyDto){
        int folio = surveyDto.getFolio();
        return placeRepository.findSurvey(folio);
    }

    @PostMapping("/search")
    public List<Map<String, Object>> findSurvey(@RequestBody SurveyDto surveyDto){
        //var result = elasticSearchService.buscarPorFolio("folios", requestDto.getFolio(), Object.class);
        RequestDto requestDto = new RequestDto();
        int folio = surveyDto.getFolio();
        try {
            String valor = String.valueOf(folio);
            var result = elasticSearchService.buscarPorFolio("folios", valor, Object.class );
            if(result != null){
                return  placeRepository.findSurvey(folio);
            }else{
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @PostMapping("/buscar")
    //public List<Map<String, Object>> getNearest(@RequestParam float lat, @RequestParam float lng) {
    public List<Map<String, Object>> getNearest(@RequestBody PlaceDto placeDto) {

        BigDecimal lat = placeDto.getLat();
        BigDecimal lng = placeDto.getLng();
        //logger.info("Latitud recibida: {}", lat);
        //logger.info("Longitud recibida: {}", lng);

        return placeRepository.findNearest(lng, lat);
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Jala bien";
    }

}
