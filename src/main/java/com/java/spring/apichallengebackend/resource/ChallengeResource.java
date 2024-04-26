package com.java.spring.apichallengebackend.resource;

import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.domain.UpdateDescriptionRequest;
import com.java.spring.apichallengebackend.usecase.ChallengeUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/api/starwars")
@RestController
@RequiredArgsConstructor
public class ChallengeResource {

    private final ChallengeUseCase challengeService;

    @PatchMapping("/movie/{episodeId}")
    public ResponseEntity<?> updateMovie(@RequestBody UpdateDescriptionRequest request, 
                                         @PathVariable Long episodeId) {

        challengeService.updateMovie(episodeId, request.getDescription());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> listMovies() {
        return ResponseEntity.ok(challengeService.listMovies());
    }

    @GetMapping("/movie/detail")
    public ResponseEntity<Movie> getMovie() {
        return ResponseEntity.ok(challengeService.getMovie(null));
    }

    @GetMapping("/movie/detail/{episodeId}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long episodeId) {
        return ResponseEntity.ok(challengeService.getMovie(episodeId));
    }

}
