package com.java.spring.apichallengebackend.usecase.impl;

import com.java.spring.apichallengebackend.helper.SagaStarWarsHelper;
import com.java.spring.apichallengebackend.repository.entity.StarWarsEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ExternalProcessDataUseCaseImplTest {

    @InjectMocks
    private ExternalProcessDataUseCaseImpl processDataUseCase;

    @Test
    void testBuildEntityList() {
        List<StarWarsEntity> entities = assertDoesNotThrow(
                () -> processDataUseCase.buildEntityList(SagaStarWarsHelper.getSagaStarWars()));

        assertNotNull(entities);
    }

}