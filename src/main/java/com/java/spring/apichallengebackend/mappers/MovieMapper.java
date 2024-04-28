package com.java.spring.apichallengebackend.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.external.dto.ExternalMovieDto;
import com.java.spring.apichallengebackend.resource.dto.MovieDto;

@Mapper
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    MovieDto map(Movie movie);

    List<MovieDto> map(List<Movie> movieList);

    Movie map(ExternalMovieDto ExternalMovieDto);

}
