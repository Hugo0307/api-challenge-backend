package com.java.spring.apichallengebackend.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "movie")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StarWarsEntity {

    @Column(name = "title")
    private String title;

    @Id
    @Column(name = "episode_id")
    private Long episode_id;

    @Setter
    @Lob
    @Column(name = "opening_crawl", length = 600)
    private String opening_crawl;

    @Column(name = "director")
    private String director;

    @Column(name = "producer")
    private String producer;

    @Column(name = "release_date")
    private String release_date;

    @Setter
    @Column(name = "version")
    private String version;
}
