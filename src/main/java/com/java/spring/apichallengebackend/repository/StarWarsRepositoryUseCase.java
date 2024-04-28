package com.java.spring.apichallengebackend.repository;

import com.java.spring.apichallengebackend.repository.entity.StarWarsEntity;

import java.util.List;

public interface StarWarsRepositoryUseCase {

    StarWarsEntity updateDescriptionMovie(Long episodeId, String description, String version);

    List<StarWarsEntity> getMovies();

    StarWarsEntity getMovie(Long episodeId);

    String getVersion(Long episodeId);

    void saveMovie(List<StarWarsEntity> entities);
}
