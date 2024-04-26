package com.java.spring.apichallengebackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StarWarsData {

    private String title;
    private Long episode_id;
    private String opening_crawl;
    private String director;
    private String producer;
    private String release_date;
}
