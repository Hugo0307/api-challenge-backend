package com.java.spring.apichallengebackend.external.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalMovieDto {

    private String title;
    private Long episode_id;
    private String opening_crawl;
    private String director;
    private String producer;
    private String release_date;
    private String version;

}
