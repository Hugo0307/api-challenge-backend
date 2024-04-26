package com.java.spring.apichallengebackend;

import com.java.spring.apichallengebackend.usecase.LoadSagaDataUsecase;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@RequiredArgsConstructor
public class ApiChallengeBackendApplication implements CommandLineRunner {

    private final LoadSagaDataUsecase loadSagaDataUsecase;

    public static void main(String[] args) {

        SpringApplication.run(ApiChallengeBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        loadSagaDataUsecase.loadSagaData();
    }
}
