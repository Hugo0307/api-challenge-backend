package com.java.spring.apichallengebackend.usecase;

import com.java.spring.apichallengebackend.domain.Movie;

import java.util.List;

public interface ChallengeUseCase {

    void updateMovie(Long episodeId, String newDescription);

    List<Movie> listMovies();

    Movie getMovie(Long episodeId);
}
