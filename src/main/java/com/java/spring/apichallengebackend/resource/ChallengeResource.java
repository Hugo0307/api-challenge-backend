package com.java.spring.apichallengebackend.resource;

import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.usecase.ChallengeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/api/starwars")
@RestController
@RequiredArgsConstructor
public class ChallengeResource {

    private final ChallengeUseCase challengeService;

    @PatchMapping("/movie/{episodeId}")
    public Movie updateMovie(@RequestBody String newDescription, @PathVariable Long movieId) {
        return challengeService.updateMovie(newDescription);
    }

    @GetMapping("/movies")
    public List<Movie> listMovies() {
        return challengeService.listMovies();
    }

    @GetMapping("/movie/detail")
    public Movie getMovie() {
        return challengeService.getMovie(null);
    }

    @GetMapping("/movie/detail/{episodeId}")
    public Movie getMovie(@PathVariable Long movieId) {
        return challengeService.getMovie(movieId);
    }

}
