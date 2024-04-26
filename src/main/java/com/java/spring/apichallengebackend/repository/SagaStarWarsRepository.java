package com.java.spring.apichallengebackend.repository;

import com.java.spring.apichallengebackend.repository.entity.StarWarsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface SagaStarWarsRepository extends JpaRepository<StarWarsEntity, Long> {

    @Modifying
    @Query("update Movie m set m.opening_crawl = :opening_crawl where m.episode_id = :episodeId")
    void updateDescriptionMovie(@Param(value = "episode_id") Long episodeId, @Param(value = "opening_crawl") String opening_crawl);
}
