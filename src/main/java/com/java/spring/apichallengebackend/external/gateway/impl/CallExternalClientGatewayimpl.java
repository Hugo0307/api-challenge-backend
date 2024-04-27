package com.java.spring.apichallengebackend.external.gateway.impl;

import com.java.spring.apichallengebackend.domain.SagaStarWars;
import com.java.spring.apichallengebackend.external.clients.StarWarsClient;
import com.java.spring.apichallengebackend.external.gateway.CallExternalClientGateway;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallExternalClientGatewayimpl implements CallExternalClientGateway {

    @Autowired
    private StarWarsClient client;

    @Override
    public Optional<SagaStarWars> executeCallExternalClient() {
        try {
            return Optional.ofNullable(client.getFilms());
        } catch (FeignException e) {
            log.error("ERROR: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
