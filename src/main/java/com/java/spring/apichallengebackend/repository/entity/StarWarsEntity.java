package com.java.spring.apichallengebackend.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class StarWarsEntity {

    private String title;

    @Id
    private Long episode_id;

    @Setter
    private String opening_crawl;

    private String director;
    private String producer;
    private String release_date;
}
