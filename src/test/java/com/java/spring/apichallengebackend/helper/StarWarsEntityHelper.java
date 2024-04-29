package com.java.spring.apichallengebackend.helper;

import com.java.spring.apichallengebackend.repository.entity.StarWarsEntity;

import java.util.List;

public class StarWarsEntityHelper {

    public static StarWarsEntity getStarWarsEntityVersion() {
        return StarWarsEntity
                .builder()
                .title("title")
                .director("director")
                .episode_id(1L)
                .opening_crawl("new description")
                .producer("producer")
                .release_date("release_date")
                .version("v2")
                .build();
    }

    public static StarWarsEntity getStarWarsEntity() {
        return StarWarsEntity
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

    public static List<StarWarsEntity> getStarWarsEntityList() {
        return List.of(getStarWarsEntity());
    }

}
