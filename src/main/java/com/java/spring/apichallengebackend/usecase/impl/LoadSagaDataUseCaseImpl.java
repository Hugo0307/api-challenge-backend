package com.java.spring.apichallengebackend.usecase.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.domain.SagaStarWars;
import com.java.spring.apichallengebackend.domain.StarWarsData;
import com.java.spring.apichallengebackend.external.gateway.CallExternalClientGateway;
import com.java.spring.apichallengebackend.repository.StarWarsRepositoryUseCase;
import com.java.spring.apichallengebackend.repository.entity.StarWarsEntity;
import com.java.spring.apichallengebackend.usecase.LoadSagaDataUsecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoadSagaDataUseCaseImpl implements LoadSagaDataUsecase {

    private final CallExternalClientGateway externalClientGateway;

    private final StarWarsRepositoryUseCase repositoryUsecase;

    @Override
    public void loadSagaData() {
        if (!isExistsDataInMemory()) {
            log.info("Request saga data from the external client");
            Optional<SagaStarWars> sagaOptional = externalClientGateway.executeCallExternalClient();

            SagaStarWars saga = sagaOptional.orElse(parseFileToModel());

            repositoryUsecase.saveMovie(buidEntityList(saga));
        }
    }

    private List<StarWarsEntity> buidEntityList( SagaStarWars saga) {
        List<StarWarsEntity> entities = new ArrayList<>();
        saga.getResults().forEach(movie -> {              
            entities.add(extractedMovie(movie));
        });
        return entities;
    }

    private StarWarsEntity extractedMovie(StarWarsData movie) {
        return StarWarsEntity
            .builder()
            .title(movie.getTitle())
            .episode_id(movie.getEpisode_id())
            .opening_crawl(movie.getOpening_crawl())
            .director(movie.getDirector())
            .producer(movie.getProducer())
            .release_date(movie.getRelease_date())
            .version("v1")
            .build();
    }

    private SagaStarWars parseFileToModel() {
        try {
            File file = loadFile();
            return new ObjectMapper().readValue(file, SagaStarWars.class);
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return null;
        }
    }

    private File loadFile() {
        try {
            ClassPathResource pathResource = new ClassPathResource("static/films-star-wars.json");
            File file = new File(String.valueOf(pathResource.getFile()));

            if (!file.exists()) {
                throw new IllegalArgumentException("File is not found: " + file.getAbsolutePath());
            }
            return file;
        } catch (IOException e) {
            log.error("Error: {}", e.getMessage());
            return null;
        }
    }

    private boolean isExistsDataInMemory() {
        List<Movie> movies = repositoryUsecase.getMovies();
        return movies.size() > 0;
    }

}
