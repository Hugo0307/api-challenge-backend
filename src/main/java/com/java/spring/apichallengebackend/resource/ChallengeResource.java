package com.java.spring.apichallengebackend.resource;

import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.domain.UpdateDescriptionRequest;
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
    public ResponseEntity<Movie> updateMovie(@RequestBody UpdateDescriptionRequest request, 
                                         @PathVariable Long episodeId) {

        return ResponseEntity.ok(challengeService.updateMovie(episodeId, request.getDescription()));
    }

    @Override
    public ResponseEntity<List<Movie>> listMovies() {
        return ResponseEntity.ok(challengeService.listMovies());
    }

    @Override
    public ResponseEntity<Movie> getMovie() {
        return ResponseEntity.ok(challengeService.getMovie(null));
    }

    @Override
    public ResponseEntity<Movie> getMovie(@PathVariable Long episodeId) {
        return ResponseEntity.ok(challengeService.getMovie(episodeId));
    }

}
