package com.java.spring.apichallengebackend.usecase.impl;

import com.java.spring.apichallengebackend.external.gateway.StarWarsExternalClientGateway;
import com.java.spring.apichallengebackend.helper.SagaStarWarsDtoHelper;
import com.java.spring.apichallengebackend.helper.SagaStarWarsHelper;
import com.java.spring.apichallengebackend.helper.StarWarsEntityHelper;
import com.java.spring.apichallengebackend.repository.StarWarsRepositoryUseCase;
import com.java.spring.apichallengebackend.repository.entity.StarWarsEntity;
import com.java.spring.apichallengebackend.usecase.ExternalProcessDataUseCase;
import com.java.spring.apichallengebackend.usecase.InternalProcessDataUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoadSagaDataUseCaseImplTest {

    @InjectMocks
    private LoadSagaDataUseCaseImpl loadSagaDataUseCase;

    @Mock
    private StarWarsExternalClientGateway externalClientGateway;

    @Mock
    private StarWarsRepositoryUseCase repositoryUseCase;

    @Mock
    private InternalProcessDataUseCase internalProcessDataUsecase;

    @Mock
    private ExternalProcessDataUseCase externalProcessDataUseCase;


    @Test
    void loadSagaDataByExternalClientIfNotDataInDataBase() {
        List<StarWarsEntity> entityList = new ArrayList<>();
        when(repositoryUseCase.getMovies()).thenReturn(entityList);
        when(externalClientGateway.executeCallExternalClient())
                .thenReturn(Optional.of(SagaStarWarsDtoHelper.getSagaStarWarsDto()));
        when(externalProcessDataUseCase.buildEntityList(any()))
                .thenReturn(StarWarsEntityHelper.getStarWarsEntityList());

        assertDoesNotThrow(() -> loadSagaDataUseCase.loadSagaData());
    }

    @Test
    void loadSagaDataByFileIfByExternalClientNotSuccessIntegration() {
        List<StarWarsEntity> entityList = new ArrayList<>();
        when(repositoryUseCase.getMovies()).thenReturn(entityList);
        when(externalClientGateway.executeCallExternalClient())
                .thenReturn(Optional.empty());
        when(externalProcessDataUseCase.buildEntityList(any()))
                .thenReturn(StarWarsEntityHelper.getStarWarsEntityList());
        when(internalProcessDataUsecase.parseDataFileToObject()).thenReturn(SagaStarWarsHelper.getSagaStarWars());

        assertDoesNotThrow(() -> loadSagaDataUseCase.loadSagaData());
    }

    @Test
    void noLoadSagaDataIfExistsDataInDataBase() {
        when(repositoryUseCase.getMovies()).thenReturn(StarWarsEntityHelper.getStarWarsEntityList());

        assertDoesNotThrow(() -> loadSagaDataUseCase.loadSagaData());
        verifyNoInteractions(externalClientGateway);
        verifyNoInteractions(internalProcessDataUsecase);
        verifyNoInteractions(externalProcessDataUseCase);
    }

}