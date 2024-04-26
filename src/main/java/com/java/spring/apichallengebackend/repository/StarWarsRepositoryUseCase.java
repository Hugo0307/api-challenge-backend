package com.java.spring.apichallengebackend.repository;

import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.repository.entity.StarWarsEntity;

import java.util.List;

public interface StarWarsRepositoryUseCase {

    void updateDescriptionMovie(Long episodeId, String description);

    List<Movie> getMovies();

    Movie getMovie(Long episodeId);

    void saveMovie(List<StarWarsEntity> entities);
}
