package com.java.spring.apichallengebackend.repository.impl;

import com.java.spring.apichallengebackend.exception.ChallengeCustomException;
import com.java.spring.apichallengebackend.helper.StarWarsEntityHelper;
import com.java.spring.apichallengebackend.repository.SagaStarWarsRepository;
import com.java.spring.apichallengebackend.repository.entity.StarWarsEntity;
import org.hibernate.QueryTimeoutException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StarWarsRepositoryUseCaseImplTest {

    @InjectMocks
    private StarWarsRepositoryUseCaseImpl repositoryUseCase;

    @Mock
    private SagaStarWarsRepository repository;


    @Test
    void testUpdateDescriptionMovie() {
        when(repository.existsById(anyLong())).thenReturn(true);
        doNothing().when(repository).updateDescriptionMovie(anyLong(), anyString(), anyString());
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(StarWarsEntityHelper.getStarWarsEntityVersion()));

        StarWarsEntity starWarsEntity = assertDoesNotThrow(
                () -> repositoryUseCase.updateDescriptionMovie(1L, "new description", "v2")
        );
        assertNotNull(starWarsEntity);
        assertEquals("v2", starWarsEntity.getVersion());
        assertEquals("new description", starWarsEntity.getOpening_crawl());
    }

    @Test
    void shouldThrowChallengeCustomExceptionIfEntityDoesNotExist() {
        when(repository.existsById(anyLong())).thenReturn(false);

        ChallengeCustomException exception = assertThrows(ChallengeCustomException.class,
                () -> repositoryUseCase.updateDescriptionMovie(1L, "new description", "v2")
        );
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertEquals("FILM NOT FOUND", exception.getError().getTitle());
        assertEquals("NOT_FOUND", exception.getError().getStatus());
        assertEquals("Film Not Found", exception.getMessage());
        assertEquals(404, exception.getError().getCode());
    }

    @Test
    void shouldThrowChallengeCustomExceptionIfErrorInUpdate() {
        when(repository.existsById(anyLong())).thenReturn(true);
        doThrow(IllegalArgumentException.class).when(repository).updateDescriptionMovie(anyLong(), anyString(), anyString());

        ChallengeCustomException exception = assertThrows(ChallengeCustomException.class,
                () -> repositoryUseCase.updateDescriptionMovie(1L, "new description", "v2")
        );
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
        assertEquals("INTERNAL ERROR", exception.getError().getTitle());
        assertEquals("INTERNAL_SERVER_ERROR", exception.getError().getStatus());
        assertEquals("Error to update data", exception.getMessage());
        assertEquals(500, exception.getError().getCode());
    }

    @Test
    void testGetMovies() {
        when(repository.findAll()).thenReturn(StarWarsEntityHelper.getStarWarsEntityList());
        assertThat( assertDoesNotThrow(() -> repositoryUseCase.getMovies()) )
                .asList()
                .isNotEmpty();
    }

    @Test
    void shouldThrowChallengeCustomExceptionIfErrorInRetrieveAllMovies() {
        when(repository.findAll()).thenThrow(QueryTimeoutException.class);

        ChallengeCustomException exception = assertThrows(ChallengeCustomException.class,
                () -> repositoryUseCase.getMovies()
        );
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
        assertEquals("INTERNAL ERROR", exception.getError().getTitle());
        assertEquals("INTERNAL_SERVER_ERROR", exception.getError().getStatus());
        assertEquals("Error to retrieve data", exception.getMessage());
        assertEquals(500, exception.getError().getCode());
    }

    @Test
    void testGetRandomMovie() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(StarWarsEntityHelper.getStarWarsEntity()));

        assertNotNull( assertDoesNotThrow(() -> repositoryUseCase.getMovie(null)) );
    }

    @Test
    void shouldThrowChallengeCustomExceptionIfRandomIdNotFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        ChallengeCustomException exception = assertThrows(ChallengeCustomException.class,
                () -> repositoryUseCase.getMovie(null)
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertEquals("FILM NOT FOUND", exception.getError().getTitle());
        assertEquals("NOT_FOUND", exception.getError().getStatus());
        assertEquals("Film Not Found", exception.getMessage());
        assertEquals(404, exception.getError().getCode());
    }

    @Test
    void testGetSpecificMovie() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(StarWarsEntityHelper.getStarWarsEntity()));

        assertNotNull( assertDoesNotThrow(() -> repositoryUseCase.getMovie(1L)) );
    }

    @Test
    void shouldThrowChallengeCustomExceptionIfSpecificIdNotFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        ChallengeCustomException exception = assertThrows(ChallengeCustomException.class,
                () -> repositoryUseCase.getMovie(1L)
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertEquals("FILM NOT FOUND", exception.getError().getTitle());
        assertEquals("NOT_FOUND", exception.getError().getStatus());
        assertEquals("Film Not Found to ID: 1", exception.getMessage());
        assertEquals(404, exception.getError().getCode());
    }

    @Test
    void testGetVersion() {
        when(repository.getVersion(anyLong())).thenReturn(Optional.of("v1"));

        assertNotNull( assertDoesNotThrow(() -> repositoryUseCase.getVersion(1L)) );
    }

    @Test
    void shouldThrowChallengeCustomExceptionIfErrorInRetrieveVersion() {
        when(repository.getVersion(anyLong())).thenReturn(Optional.empty());

        ChallengeCustomException exception = assertThrows(ChallengeCustomException.class,
                () -> repositoryUseCase.getVersion(1L)
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertEquals("FILM NOT FOUND", exception.getError().getTitle());
        assertEquals("NOT_FOUND", exception.getError().getStatus());
        assertEquals("Film Not Found to ID: 1", exception.getMessage());
        assertEquals(404, exception.getError().getCode());
    }

    @Test
    void testSaveMovie() {
        assertDoesNotThrow(
                () -> repositoryUseCase.saveMovie(StarWarsEntityHelper.getStarWarsEntityList())
        );
    }

    @Test
    void shouldThrowChallengeCustomExceptionIfErrorInSaveMovies() {
        when(repository.saveAll(anyList())).thenThrow(IllegalArgumentException.class);

        ChallengeCustomException exception = assertThrows(ChallengeCustomException.class,
                () -> repositoryUseCase.saveMovie(StarWarsEntityHelper.getStarWarsEntityList())
        );
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
        assertEquals("INTERNAL ERROR", exception.getError().getTitle());
        assertEquals("INTERNAL_SERVER_ERROR", exception.getError().getStatus());
        assertEquals("Operation internal error", exception.getMessage());
        assertEquals(500, exception.getError().getCode());
    }

}