package com.java.spring.apichallengebackend.usecase;

import com.java.spring.apichallengebackend.domain.Movie;

import java.util.List;

public interface ChallengeUseCase {

    Movie updateMovie(Long episodeId, String newDescription);

    List<Movie> listMovies();

    Movie getMovie(Long episodeId);
}
