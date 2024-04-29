package com.java.spring.apichallengebackend.helper;

import com.java.spring.apichallengebackend.external.dto.SagaStarWarsDto;

public class SagaStarWarsDtoHelper {

    public static SagaStarWarsDto getSagaStarWarsDto() {
        return SagaStarWarsDto
                .builder()
                .results(ExternalMovieDtoHelper.getExternalMovieDtoList())
                .build();
    }
}
