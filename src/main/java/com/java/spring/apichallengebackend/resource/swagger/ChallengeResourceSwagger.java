package com.java.spring.apichallengebackend.resource.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.spring.apichallengebackend.domain.Movie;
import com.java.spring.apichallengebackend.domain.UpdateDescriptionRequest;
import com.java.spring.apichallengebackend.exception.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(name = "API CHALLENGE BACKEND DEVELOPER", 
description = "API construída para atender requisitos de um desafio backend")
@RequestMapping("/v1/api/starwars")
public interface ChallengeResourceSwagger {

    @Operation(summary = "Atualiza a descrição (opening crawl) de um determinado filme da saga Star Wars",
    description = "Recebe um body com a nova descrição e um id do episódio a ter a descrição alterada")
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "204", description = "Operação realizada com sucesso", content = @Content(schema = @Schema(implementation = Movie.class))),
                    @ApiResponse(responseCode = "400", description = "Id fornecido é inválido", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Episódio inexistente, não encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno durante operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping(value = "/movie/{episodeId}", consumes = {"application/json"})
    ResponseEntity<Movie> updateMovie(
        @Parameter(description = "Nova descrição para o filme", required = true) @Valid @RequestBody UpdateDescriptionRequest request, 
        @Parameter(description = "Id do episódio que terá a descrição alterada", required = true) @PathVariable Long episodeId);


    @Operation(summary = "Lista todos os filmes da saga",
    description = "Devolve uma lista com informações de todos os filmes da saga Star Wars")
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso", content = @Content(schema = @Schema(implementation = Movie.class))),
                    @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno durante operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/movies")
    ResponseEntity<List<Movie>> listMovies();

    
    @Operation(summary = "Lista detalhes de um filme",
    description = "Detalha informações de um filme da saga Star Wars de forma aleatória")
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso", content = @Content(schema = @Schema(implementation = Movie.class))),
                    @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno durante operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/movie/detail")
    ResponseEntity<Movie> getMovie();


    @Operation(summary = "Lista detalhes de um filme específico",
    description = "Recebe o id do episódio do filme e detalha informações de um filme específico da saga Star Wars")
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso", content = @Content(schema = @Schema(implementation = Movie.class))),
                    @ApiResponse(responseCode = "400", description = "Id fornecido é inválido", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Episódio inexistente, não encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Ocorreu um erro interno durante operação", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/movie/detail/{episodeId}")
    ResponseEntity<Movie> getMovie(@Parameter(description = "Id do episódio a ser consultado", required = true) @PathVariable Long episodeId);
}
