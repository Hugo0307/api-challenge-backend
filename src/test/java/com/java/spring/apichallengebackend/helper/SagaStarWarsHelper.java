package com.java.spring.apichallengebackend.helper;

import com.java.spring.apichallengebackend.domain.SagaStarWars;

public class SagaStarWarsHelper {

    public static SagaStarWars getSagaStarWars() {
        return SagaStarWars
                .builder()
                .results(MovieHelper.getMovieList())
                .build();
    }
}
