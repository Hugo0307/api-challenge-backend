package com.java.spring.apichallengebackend.external.clients;

import com.java.spring.apichallengebackend.external.dto.SagaStarWarsDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "swapi", url = "https://swapi.dev/api")
public interface StarWarsClient {

    @GetMapping(value = "/films", consumes = MediaType.APPLICATION_JSON_VALUE)
    SagaStarWarsDto getFilms();
}
