package com.java.spring.apichallengebackend.external.gateway.impl;

import com.java.spring.apichallengebackend.external.clients.StarWarsClient;
import com.java.spring.apichallengebackend.external.dto.SagaStarWarsDto;
import com.java.spring.apichallengebackend.external.gateway.StarWarsExternalClientGateway;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StarWarsExternalClientGatewayimpl implements StarWarsExternalClientGateway {

    private final StarWarsClient client;

    @Override
    public Optional<SagaStarWarsDto> executeCallExternalClient() {
        try {
            return Optional.ofNullable(client.getFilms());
        } catch (FeignException e) {
            log.error("Integration external with error, recuvering data by file");
            return Optional.empty();
        }
    }
}
