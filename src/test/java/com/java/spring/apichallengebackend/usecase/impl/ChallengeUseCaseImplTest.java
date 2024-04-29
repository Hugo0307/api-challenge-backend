package com.java.spring.apichallengebackend.usecase.impl;

import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.helper.StarWarsEntityHelper;
import com.java.spring.apichallengebackend.repository.StarWarsRepositoryUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChallengeUseCaseImplTest {

    @InjectMocks
    private ChallengeUseCaseImpl challengeUseCase;

    @Mock
    private StarWarsRepositoryUseCase repositoryUseCase;

    private final long episodeId = 1L;

    @Test
    void updateMovie() {
        String currentVersion = "v1";
        String newVersion = "v2";
        String newDescription = "new description";

        when(repositoryUseCase.getVersion(anyLong())).thenReturn(currentVersion);

        when(repositoryUseCase.updateDescriptionMovie(anyLong(), anyString(), anyString()))
                .thenReturn(StarWarsEntityHelper.getStarWarsEntityVersion());

        Movie updatedMovie = assertDoesNotThrow(() -> challengeUseCase.updateMovie(episodeId, newDescription));
        assertNotNull(updatedMovie);
        assertEquals(newVersion, updatedMovie.getVersion());
        assertEquals(newDescription, updatedMovie.getOpening_crawl());
        assertEquals(episodeId, updatedMovie.getEpisode_id());
    }

    @Test
    void listMovies() {
        when(repositoryUseCase.getMovies()).thenReturn(StarWarsEntityHelper.getStarWarsEntityList());

        List<Movie> movieList = assertDoesNotThrow(() -> challengeUseCase.listMovies());

        assertNotNull(movieList);
        assertThat(movieList)
                .isNotNull()
                .asList()
                .isNotEmpty();
    }

    @Test
    void getMovie() {
        when(repositoryUseCase.getMovie(episodeId)).thenReturn(StarWarsEntityHelper.getStarWarsEntity());

        Movie movie = assertDoesNotThrow(() -> challengeUseCase.getMovie(episodeId));

        assertNotNull(movie);
        assertEquals(episodeId, movie.getEpisode_id());
    }

    @Test
    void getRandomMovie() {
        when(repositoryUseCase.getMovie(null)).thenReturn(StarWarsEntityHelper.getStarWarsEntity());

        Movie movie = assertDoesNotThrow(() -> challengeUseCase.getMovie(null));

        assertNotNull(movie);
        assertEquals(episodeId, movie.getEpisode_id());
    }
}