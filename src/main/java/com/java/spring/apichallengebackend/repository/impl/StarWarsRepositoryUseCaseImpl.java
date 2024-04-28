package com.java.spring.apichallengebackend.repository.impl;

import com.java.spring.apichallengebackend.enums.ChallengeTypeErrorEnum;
import com.java.spring.apichallengebackend.exception.ChallengeCustomException;
import com.java.spring.apichallengebackend.repository.SagaStarWarsRepository;
import com.java.spring.apichallengebackend.repository.StarWarsRepositoryUseCase;
import com.java.spring.apichallengebackend.repository.entity.StarWarsEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class StarWarsRepositoryUseCaseImpl implements StarWarsRepositoryUseCase {

    private final SagaStarWarsRepository repository;

    @Override
    @Transactional
    public StarWarsEntity updateDescriptionMovie(Long episodeId, String description, String version) {
        boolean isExistEntity = repository.existsById(episodeId);
        if (!isExistEntity) {
            throw new ChallengeCustomException(
                ChallengeTypeErrorEnum.FILM_NOT_FOUND, 
                HttpStatus.NOT_FOUND,
                "Film Not Found");
        }

        try {
            repository.updateDescriptionMovie(episodeId, description, version);
            return getMovie(episodeId);
        } catch (Exception e) {
            log.error("An error occurred while update data from the database {}", e.getMessage());
            throw new ChallengeCustomException(
                ChallengeTypeErrorEnum.INTERNAL_ERROR, 
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Error to update data");
        }
    }

    @Override
    public List<StarWarsEntity> getMovies() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            log.error("An error occurred while trying to retrieve data from the database {}", e.getMessage());
            throw new ChallengeCustomException(
                ChallengeTypeErrorEnum.INTERNAL_ERROR, 
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Error to retrieve data");
        }
    }

    @Override
    public StarWarsEntity getMovie(Long episodeId) {
        
        if (isNull(episodeId)) {
            Long generateRandomEpisodeId = new Random().nextLong(1L, 7L);
            Optional<StarWarsEntity> entityOptional = repository.findById(generateRandomEpisodeId);
            return entityOptional
                    .orElseThrow(() -> new ChallengeCustomException(
                        ChallengeTypeErrorEnum.FILM_NOT_FOUND, 
                        HttpStatus.NOT_FOUND,
                        "Film Not Found")
                    );

        } else {
            Optional<StarWarsEntity> entityOptional = repository.findById(episodeId);
            return entityOptional
                    .orElseThrow(() -> new ChallengeCustomException(
                        ChallengeTypeErrorEnum.FILM_NOT_FOUND, 
                        HttpStatus.NOT_FOUND,
                        "Film Not Found to ID: " + episodeId)
                    );
        }
    }

    @Override
    public String getVersion(Long episodeId) {
        return repository
            .getVersion(episodeId)
            .orElseThrow(() -> new ChallengeCustomException(
                ChallengeTypeErrorEnum.FILM_NOT_FOUND,
                HttpStatus.NOT_FOUND,
                "Film Not Found to ID: " + episodeId)
            );          
    }

    @Override
    public void saveMovie(List<StarWarsEntity> entities) {
        try {
            repository.saveAll(entities);
        } catch (Exception e) {
            log.error("An error ocurred while save data: {}", e.getMessage());
            throw new ChallengeCustomException(
                ChallengeTypeErrorEnum.INTERNAL_ERROR, 
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Operation internal error");
        }
        
    }

}
