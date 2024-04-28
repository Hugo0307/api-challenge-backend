package com.java.spring.apichallengebackend.usecase.impl;

import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.domain.SagaStarWars;
import com.java.spring.apichallengebackend.external.gateway.StarWarsExternalClientGateway;
import com.java.spring.apichallengebackend.repository.StarWarsRepositoryUseCase;
import com.java.spring.apichallengebackend.repository.entity.StarWarsEntity;
import com.java.spring.apichallengebackend.usecase.InternalProcessDataUseCase;
import com.java.spring.apichallengebackend.usecase.LoadSagaDataUsecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoadSagaDataUseCaseImpl implements LoadSagaDataUsecase {

    private final StarWarsExternalClientGateway externalClientGateway;

    private final StarWarsRepositoryUseCase repositoryUsecase;

    private final InternalProcessDataUseCase internalProcessDataUsecase;

    @Override
    public void loadSagaData() {
        if (!isExistsDataInMemory()) {
            log.info("Request saga data from the external client");
            Optional<SagaStarWars> sagaOptional = externalClientGateway.executeCallExternalClient();

            SagaStarWars saga = sagaOptional.orElse(internalProcessDataUsecase.parseDataFileToObject());

            repositoryUsecase.saveMovie(buildEntityList(saga));
        }
    }

    private List<StarWarsEntity> buildEntityList( SagaStarWars saga) {
        List<StarWarsEntity> entities = new ArrayList<>();
        saga.getResults().forEach(movie -> {              
            entities.add(buildEntity(movie));
        });
        return entities;
    }

    private StarWarsEntity buildEntity(Movie movie) {
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

    private boolean isExistsDataInMemory() {
        List<Movie> movies = repositoryUsecase.getMovies();
        return movies.size() > 0;
    }

}
