package com.java.spring.apichallengebackend.resource;

import com.java.spring.apichallengebackend.helper.MovieHelper;
import com.java.spring.apichallengebackend.resource.dto.MovieDto;
import com.java.spring.apichallengebackend.usecase.ChallengeUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChallengeResourceTest {

    @InjectMocks
    private ChallengeResource resource;

    @Mock
    private ChallengeUseCase challengeService;

    private final long episodeId = 1L;

    @Test
    void whenRequestingFilmWithoutProvidingEpisodeIdShouldReturnRandomFilm() {
        when(challengeService.getMovie(null)).thenReturn(MovieHelper.getMovie());

        ResponseEntity<MovieDto> responseEntity = assertDoesNotThrow(() -> resource.getMovie());

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        assertThat(responseEntity.getBody())
                .isNotNull()
                .isExactlyInstanceOf(MovieDto.class)
                .hasOnlyFields(
                        "title",
                        "episode_id",
                        "opening_crawl",
                        "director",
                        "producer",
                        "release_date",
                        "version");
    }

    @Test
    void whenRequestingFilmProvidingEpisodeIdShouldReturnFilmWithSpecifiedId() {
        when(challengeService.getMovie(episodeId)).thenReturn(MovieHelper.getMovie());

        ResponseEntity<MovieDto> responseEntity = assertDoesNotThrow(() -> resource.getMovie(episodeId));

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        assertThat(responseEntity.getBody())
                .isNotNull()
                .isExactlyInstanceOf(MovieDto.class)
                .hasOnlyFields(
                        "title",
                        "episode_id",
                        "opening_crawl",
                        "director",
                        "producer",
                        "release_date",
                        "version")
                .extracting(MovieDto::getEpisode_id)
                .isEqualTo(episodeId);
    }

    @Test
    void ShouldReturnFilmList() {
        when(challengeService.listMovies()).thenReturn(MovieHelper.getMovieList());

        ResponseEntity<List<MovieDto>> responseEntity = assertDoesNotThrow(() -> resource.listMovies());

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());
        assertThat(responseEntity.getBody())
                .isNotNull()
                .asList()
                .isNotEmpty();
    }

    @Test
    void whenRequestingUpdateFilmShouldReturnUpdatedFilm() {
        Long episodeId = 1L;
        UpdateDescriptionRequest request = UpdateDescriptionRequest.builder().description("description").build();

        when(challengeService.updateMovie(anyLong(), anyString())).thenReturn(MovieHelper.getMovie());

        ResponseEntity<MovieDto> responseEntity = assertDoesNotThrow(() -> resource.updateMovie(request, episodeId));

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCode().value());

        var response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals(episodeId, response.getEpisode_id());
        assertEquals(request.getDescription(), response.getOpening_crawl());
    }
}
