package com.java.spring.apichallengebackend.repository;

import com.java.spring.apichallengebackend.repository.entity.StarWarsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SagaStarWarsRepository extends JpaRepository<StarWarsEntity, Long> {

    @Modifying
    @Query(value = "update movie m set m.opening_crawl = :opening_crawl, m.version = :version where m.episode_id = :episode_id")
    void updateDescriptionMovie(@Param(value = "episode_id") Long episode_id, 
                                @Param(value = "opening_crawl") String opening_crawl,
                                @Param(value = "version") String version);
}
