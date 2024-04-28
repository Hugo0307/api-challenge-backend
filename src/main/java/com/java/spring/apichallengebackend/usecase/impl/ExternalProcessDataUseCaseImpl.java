package com.java.spring.apichallengebackend.usecase.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.domain.SagaStarWars;
import com.java.spring.apichallengebackend.repository.entity.StarWarsEntity;
import com.java.spring.apichallengebackend.usecase.ExternalProcessDataUseCase;

@Service
public class ExternalProcessDataUseCaseImpl implements ExternalProcessDataUseCase{

    public List<StarWarsEntity> buildEntityList( SagaStarWars saga) {
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

}
