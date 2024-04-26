package com.java.spring.apichallengebackend.usecase.impl;

import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.repository.StarWarsRepositoryUseCase;
import com.java.spring.apichallengebackend.usecase.ChallengeUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChallengeUseCaseImpl implements ChallengeUseCase {

    private final StarWarsRepositoryUseCase repositoryUseCase;

    @Override
    public void updateMovie(Long episodeId, String newDescription) {

        String newVersion = getNewVersion(episodeId);
        log.info("Updating version to {}", newVersion);
        repositoryUseCase.updateDescriptionMovie(episodeId, newDescription, newVersion);
    }

    @Override
    public List<Movie> listMovies() {
        return repositoryUseCase.getMovies();
    }

    @Override
    public Movie getMovie(Long episodeId) {
        return repositoryUseCase.getMovie(episodeId);
    }

    private String getNewVersion(Long episodeId) {
        Movie movie = repositoryUseCase.getMovie(episodeId);
        String version = movie.getVersion();
        int incrementalVersion = Integer.parseInt(version.substring(1));
        return "v".concat(String.valueOf(incrementalVersion+1));
    }
}
