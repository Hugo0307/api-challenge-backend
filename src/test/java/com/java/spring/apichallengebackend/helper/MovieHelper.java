package com.java.spring.apichallengebackend.helper;

import com.java.spring.apichallengebackend.domain.Movie;

import java.util.List;

public class MovieHelper {

    public static Movie getMovie() {
        return Movie
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

    public static List<Movie> getMovieList() {
        return List.of(getMovie());
    }
}
