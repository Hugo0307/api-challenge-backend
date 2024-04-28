package com.java.spring.apichallengebackend.external.gateway;


import java.util.Optional;

import com.java.spring.apichallengebackend.external.dto.SagaStarWarsDto;

public interface StarWarsExternalClientGateway {

    Optional<SagaStarWarsDto> executeCallExternalClient();
}
