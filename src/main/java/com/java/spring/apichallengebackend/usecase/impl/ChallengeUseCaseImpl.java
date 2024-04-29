package com.java.spring.apichallengebackend.usecase.impl;

import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.mappers.StarWarsEntityMapper;
import com.java.spring.apichallengebackend.repository.StarWarsRepositoryUseCase;
import com.java.spring.apichallengebackend.usecase.ChallengeUseCase;

import com.java.spring.apichallengebackend.utils.GenerateVersion;
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
    public Movie updateMovie(Long episodeId, String newDescription) {

        String newVersion = GenerateVersion.getNewVersion(repositoryUseCase.getVersion(episodeId));
        log.info("Updating version to {}", newVersion);
        return StarWarsEntityMapper.INSTANCE.map(
            repositoryUseCase.updateDescriptionMovie(episodeId, newDescription, newVersion)
        );
    }

    @Override
    public List<Movie> listMovies() {
        return StarWarsEntityMapper.INSTANCE.map(repositoryUseCase.getMovies());
    }

    @Override
    public Movie getMovie(Long episodeId) {
        return StarWarsEntityMapper.INSTANCE.map(repositoryUseCase.getMovie(episodeId));
    }
}
