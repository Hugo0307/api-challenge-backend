package com.java.spring.apichallengebackend.external.gateway;


import java.util.Optional;

import com.java.spring.apichallengebackend.domain.SagaStarWars;

public interface CallExternalClientGateway {

    Optional<SagaStarWars> executeCallExternalClient();
}
