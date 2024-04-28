package com.java.spring.apichallengebackend.usecase.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.spring.apichallengebackend.domain.SagaStarWars;
import com.java.spring.apichallengebackend.usecase.InternalProcessDataUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InternalProcessDataUseCaseImpl implements InternalProcessDataUseCase{

    @Override
    public SagaStarWars parseDataFileToObject() {
        try {
            File file = loadFile();
            return new ObjectMapper().readValue(file, SagaStarWars.class);
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return null;
        }
    }

    private File loadFile() {
        try {
            ClassPathResource pathResource = new ClassPathResource("static/films-star-wars.json");
            File file = new File(String.valueOf(pathResource.getFile()));

            if (!file.exists()) {
                throw new IllegalArgumentException("File is not found: " + file.getAbsolutePath());
            }
            return file;
        } catch (IOException e) {
            log.error("Error: {}", e.getMessage());
            return null;
        }
    }

}
