package com.java.spring.apichallengebackend.usecase.impl;

import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.domain.SagaStarWars;
import com.java.spring.apichallengebackend.external.gateway.CallExternalClientGateway;
import com.java.spring.apichallengebackend.repository.StarWarsRepositoryUseCase;
import com.java.spring.apichallengebackend.repository.entity.StarWarsEntity;
import com.java.spring.apichallengebackend.usecase.LoadSagaDataUsecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

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
            SagaStarWars saga = externalClientGateway.executeCallExternalClient();

            saga.getResults().forEach(movie -> {
                List<StarWarsEntity> entities = new ArrayList<>();
                entities.add(StarWarsEntity
                    .builder()
                    .title(movie.getTitle())
                    .episode_id(movie.getEpisode_id())
                    .opening_crawl(movie.getOpening_crawl())
                    .director(movie.getDirector())
                    .producer(movie.getProducer())
                    .release_date(movie.getRelease_date())
                    .build());
                repositoryUsecase.saveMovie(entities);
            });
        }
        log.info("Load saga data by database in memory");
    }

    private boolean isExistsDataInMemory() {
        List<Movie> movies = repositoryUsecase.getMovies();
        return movies.size() > 0;
    }


}
