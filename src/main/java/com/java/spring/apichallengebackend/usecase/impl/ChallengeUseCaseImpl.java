package com.java.spring.apichallengebackend.usecase.impl;

import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.usecase.ChallengeUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeUseCaseImpl implements ChallengeUseCase {

    @Override
    public Movie updateMovie(String newDescription) {

        return null;
    }

    @Override
    public List<Movie> listMovies() {
        return List.of();
    }

    @Override
    public Movie getMovie(Long movieId) {
        return null;
    }
}
