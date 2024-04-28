package com.java.spring.apichallengebackend.resource;

import com.java.spring.apichallengebackend.mappers.MovieMapper;
import com.java.spring.apichallengebackend.resource.dto.MovieDto;
import com.java.spring.apichallengebackend.resource.swagger.ChallengeResourceSwagger;
import com.java.spring.apichallengebackend.usecase.ChallengeUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ChallengeResource implements ChallengeResourceSwagger{

    private final ChallengeUseCase challengeService;

    @Override
    public ResponseEntity<MovieDto> updateMovie(@RequestBody UpdateDescriptionRequest request, 
                                                @PathVariable Long episodeId) {

        return ResponseEntity.ok(
            MovieMapper.INSTANCE.map(
                challengeService.updateMovie(episodeId, request.getDescription())
            )
        );
    }

    @Override
    public ResponseEntity<List<MovieDto>> listMovies() {
        return ResponseEntity.ok(MovieMapper.INSTANCE.map(challengeService.listMovies()));
    }

    @Override
    public ResponseEntity<MovieDto> getMovie() {
        return ResponseEntity.ok(MovieMapper.INSTANCE.map(challengeService.getMovie(null)));
    }

    @Override
    public ResponseEntity<MovieDto> getMovie(@PathVariable Long episodeId) {
        return ResponseEntity.ok(MovieMapper.INSTANCE.map(challengeService.getMovie(episodeId)));
    }

}
