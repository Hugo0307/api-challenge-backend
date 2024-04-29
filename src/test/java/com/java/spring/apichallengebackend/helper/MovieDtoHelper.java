package com.java.spring.apichallengebackend.helper;

import com.java.spring.apichallengebackend.resource.dto.MovieDto;

import java.util.List;

public class MovieDtoHelper {

    public static MovieDto getMovieDto() {
        return MovieDto
                .builder()
                .title("title")
                .director("director")
                .episode_id(1L)
                .opening_crawl("description")
                .producer("producer")
                .release_date("release_date")
                .version("v1")
                .build();
    }

    public static List<MovieDto> getMovieDtoList() {
        return List.of(getMovieDto());
    }
}
