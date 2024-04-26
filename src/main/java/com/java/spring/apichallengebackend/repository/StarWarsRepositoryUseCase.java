package com.java.spring.apichallengebackend.repository;

import com.java.spring.apichallengebackend.domain.Movie;

import java.util.List;

public interface StarWarsRepositoryUseCase {

    void updateDescriptionMovie(Long episodeId, String description);

    List<Movie> getMovies();

    Movie getMovie(Long episodeId);
}
