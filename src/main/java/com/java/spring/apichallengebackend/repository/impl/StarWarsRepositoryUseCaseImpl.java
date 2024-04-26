package com.java.spring.apichallengebackend.repository.impl;

import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.repository.SagaStarWarsRepository;
import com.java.spring.apichallengebackend.repository.StarWarsRepositoryUseCase;
import com.java.spring.apichallengebackend.repository.entity.StarWarsEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class StarWarsRepositoryUseCaseImpl implements StarWarsRepositoryUseCase {

    private final SagaStarWarsRepository repository;

    @Override
    @Transactional
    public void updateDescriptionMovie(Long episodeId, String description, String version) {
        boolean isExistEntity = repository.existsById(episodeId);
        if (!isExistEntity) {
            throw new IllegalArgumentException("Film Not Found to ID: " + episodeId);
        }

        try {
            repository.updateDescriptionMovie(episodeId, description, version);
        } catch (Exception e) {
            log.error("An error occurred while update data from the database {}", e);
        }
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
                Long generateRandomEpisodeId = new Random().nextLong(1L, 7L);
                Optional<StarWarsEntity> entityOptional = repository.findById(generateRandomEpisodeId);
                movie = entityOptional
                        .map(StarWarsRepositoryUseCaseImpl::buildMovie)
                        .orElseThrow(() -> new IllegalArgumentException("Films Not Found"));

            } else {
                Optional<StarWarsEntity> entityOptional = repository.findById(episodeId);
                movie = entityOptional
                        .map(StarWarsRepositoryUseCaseImpl::buildMovie)
                        .orElseThrow(() -> new IllegalArgumentException("Film Not Found to ID: " + episodeId));
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
                .version(entity.getVersion())
                .build();
    }

    @Override
    public void saveMovie(List<StarWarsEntity> entities) {
        try {
            repository.saveAll(entities);
        } catch (Exception e) {
            log.error("An error ocurred while save data: {}", e.getMessage());
        }
        
    }
}
