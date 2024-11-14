package com.example.mapasearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.example.mapasearch.repository")
public class MapaSearchApplication {

    public static void main(String[] args) {

        SpringApplication.run(MapaSearchApplication.class, args);
    }

}
