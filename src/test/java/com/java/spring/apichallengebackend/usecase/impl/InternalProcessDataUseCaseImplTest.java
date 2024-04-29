package com.java.spring.apichallengebackend.usecase.impl;

import com.java.spring.apichallengebackend.domain.SagaStarWars;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class InternalProcessDataUseCaseImplTest {

    @InjectMocks
    private InternalProcessDataUseCaseImpl processDataUseCase;

    @Test
    void parseDataFileToObjectSuccess() {
        assertThat( assertDoesNotThrow(() -> processDataUseCase.parseDataFileToObject()) )
                .isNotNull()
                .isExactlyInstanceOf(SagaStarWars.class);
    }

}