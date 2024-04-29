package com.java.spring.apichallengebackend.usecase.impl;

import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.domain.SagaStarWars;
import com.java.spring.apichallengebackend.external.gateway.StarWarsExternalClientGateway;
import com.java.spring.apichallengebackend.mappers.SagaStarWarsMapper;
import com.java.spring.apichallengebackend.mappers.StarWarsEntityMapper;
import com.java.spring.apichallengebackend.repository.StarWarsRepositoryUseCase;
import com.java.spring.apichallengebackend.usecase.ExternalProcessDataUseCase;
import com.java.spring.apichallengebackend.usecase.InternalProcessDataUseCase;
import com.java.spring.apichallengebackend.usecase.LoadSagaDataUsecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoadSagaDataUseCaseImpl implements LoadSagaDataUsecase {

    private final StarWarsExternalClientGateway externalClientGateway;

    private final StarWarsRepositoryUseCase repositoryUseCase;

    private final InternalProcessDataUseCase internalProcessDataUsecase;

    private final ExternalProcessDataUseCase externalProcessDataUseCase;

    @Override
    public void loadSagaData() {
        if (!isExistsDataInMemory()) {
            log.info("Request saga data from the external client");
            Optional<SagaStarWars> sagaOptional = SagaStarWarsMapper.map(externalClientGateway.executeCallExternalClient());

            SagaStarWars saga = sagaOptional.orElse(internalProcessDataUsecase.parseDataFileToObject());

            repositoryUseCase.saveMovie(externalProcessDataUseCase.buildEntityList(saga));
        }
    }

    private boolean isExistsDataInMemory() {
        List<Movie> movies = StarWarsEntityMapper.INSTANCE.map(repositoryUseCase.getMovies());
        return !movies.isEmpty();
    }

}
