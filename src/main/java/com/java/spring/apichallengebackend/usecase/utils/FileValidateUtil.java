package com.java.spring.apichallengebackend.usecase.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Slf4j
@Component
public class FileValidateUtil {

    public static boolean isExistsDataInFile() {
        boolean isExists = false;
        try {
            ClassPathResource pathResource = new ClassPathResource("static/films-star-wars.json");
            File file = new File(String.valueOf(pathResource.getFile()));

            if (!file.exists()) {
                throw new IllegalArgumentException("File is not found: " + file.getAbsolutePath());
            }

            isExists = file.length() == 0;
        } catch (IOException e) {
            log.error("Error: {}", e.getMessage());
        }

        return isExists;
    }
}
