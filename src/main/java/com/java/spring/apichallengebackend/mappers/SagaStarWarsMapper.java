package com.java.spring.apichallengebackend.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.domain.SagaStarWars;
import com.java.spring.apichallengebackend.external.dto.SagaStarWarsDto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SagaStarWarsMapper {

    public static Optional<SagaStarWars> map(Optional<SagaStarWarsDto> sagaOptional) {

        if (sagaOptional.isEmpty()) return Optional.empty();

        SagaStarWarsDto sagaStarWarsDto = sagaOptional.get();
        List<Movie> results = new ArrayList<>();
        sagaStarWarsDto.getResults().forEach(externalMovieDto -> {
            Movie movie = MovieMapper.INSTANCE.map(externalMovieDto);
            results.add(movie);
        });

        return Optional.of(SagaStarWars.builder().results(results).build());
    }

}
