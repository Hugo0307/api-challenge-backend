package com.java.spring.apichallengebackend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Movie {

    private String title;
    private Long episode_id;

    @Setter
    private String opening_crawl;

    private String director;
    private String producer;
    private String release_date;
}
