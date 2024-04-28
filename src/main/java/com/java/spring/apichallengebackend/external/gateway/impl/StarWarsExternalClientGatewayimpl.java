package com.java.spring.apichallengebackend.external.gateway.impl;

import com.java.spring.apichallengebackend.domain.SagaStarWars;
import com.java.spring.apichallengebackend.external.clients.StarWarsClient;
import com.java.spring.apichallengebackend.external.gateway.StarWarsExternalClientGateway;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StarWarsExternalClientGatewayimpl implements StarWarsExternalClientGateway {

    @Autowired
    private StarWarsClient client;

    @Override
    public Optional<SagaStarWars> executeCallExternalClient() {
        try {
            return Optional.ofNullable(client.getFilms());
        } catch (FeignException e) {
            log.error("Integration external with error, geting data by file");
            return Optional.empty();
        }
    }
}
