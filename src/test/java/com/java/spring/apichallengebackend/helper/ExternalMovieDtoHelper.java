package com.java.spring.apichallengebackend.helper;

import com.java.spring.apichallengebackend.external.dto.ExternalMovieDto;

import java.util.List;

public class ExternalMovieDtoHelper {

    public static ExternalMovieDto getExternalMovieDto() {
        return ExternalMovieDto
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

    public static List<ExternalMovieDto> getExternalMovieDtoList() {
        return List.of(getExternalMovieDto());
    }
}
