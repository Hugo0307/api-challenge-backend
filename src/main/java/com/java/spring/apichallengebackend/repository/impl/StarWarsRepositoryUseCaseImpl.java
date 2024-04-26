package com.java.spring.apichallengebackend.repository.impl;

import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.repository.SagaStarWarsRepository;
import com.java.spring.apichallengebackend.repository.StarWarsRepositoryUseCase;
import com.java.spring.apichallengebackend.repository.entity.StarWarsEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class StarWarsRepositoryUseCaseImpl implements StarWarsRepositoryUseCase {

    @Autowired
    private SagaStarWarsRepository repository;

    @Override
    public void updateDescriptionMovie(Long episodeId, String description) {
        repository.updateDescriptionMovie(episodeId, description);
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movieList = new ArrayList<>();
        try {
            var moviesEntity = repository.findAll();
            moviesEntity.forEach(entity -> {
                movieList.add(buildMovie(entity)
                );
            });
        } catch (Exception e) {
            log.error("An error occurred while trying to retrieve data from the database {}", e.getMessage());
        }
        return movieList;
    }

    @Override
    public Movie getMovie(Long episodeId) {
        Movie movie = null;
        try {
            if (isNull(episodeId)) {
                Optional<StarWarsEntity> entityOptional = repository.findOne(Example.of(new StarWarsEntity()));
                movie = entityOptional
                        .map(StarWarsRepositoryUseCaseImpl::buildMovie)
                        .orElseThrow();

            } else {
                Optional<StarWarsEntity> entityOptional = repository.findById(episodeId);
                movie = entityOptional
                        .map(StarWarsRepositoryUseCaseImpl::buildMovie)
                        .orElseThrow();
            }

        } catch (Exception e) {
            log.error("An error occurred while trying to retrieve data from the database {}", e.getMessage());
        }
        return  movie;
    }

    private static Movie buildMovie(StarWarsEntity entity) {
        return Movie
                .builder()
                .title(entity.getTitle())
                .episode_id(entity.getEpisode_id())
                .opening_crawl(entity.getOpening_crawl())
                .director(entity.getDirector())
                .producer(entity.getProducer())
                .release_date(entity.getRelease_date())
                .build();
    }
}
