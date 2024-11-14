package com.example.mapasearch.repository;

import com.example.mapasearch.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query(value = "SELECT * FROM funcion_buscar_proximo ( ?, ?);", nativeQuery = true)
    List<Map<String, Object>> findNearest(@Param("lat") BigDecimal lat, @Param("lng") BigDecimal lng);

    @Query(value="SELECT * FROM obtener_datos_por_folio(?);", nativeQuery = true)
    List<Map<String, Object>> findSurvey(@Param("folio") int folio);

}
