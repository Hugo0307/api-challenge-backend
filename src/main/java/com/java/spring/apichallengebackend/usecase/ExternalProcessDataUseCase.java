package com.java.spring.apichallengebackend.usecase;

import java.util.List;

import com.java.spring.apichallengebackend.domain.SagaStarWars;
import com.java.spring.apichallengebackend.repository.entity.StarWarsEntity;

public interface ExternalProcessDataUseCase {

    List<StarWarsEntity> buildEntityList( SagaStarWars saga);

}
