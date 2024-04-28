package com.java.spring.apichallengebackend.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.repository.entity.StarWarsEntity;

@Mapper
public interface StarWarsEntityMapper {

    StarWarsEntityMapper INSTANCE = Mappers.getMapper(StarWarsEntityMapper.class);

    StarWarsEntity map(Movie movie);

    Movie map(StarWarsEntity entity);

    List<Movie> map(List<StarWarsEntity> entity);

}
