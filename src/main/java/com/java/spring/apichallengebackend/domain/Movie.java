package com.java.spring.apichallengebackend.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Movie {

    @Schema(description = "Título do episódio")
    private String title;

    @Schema(description = "Identificador único do episódio")
    private Long episode_id;

    @Setter
    @Schema(description = "Descrição do filme")
    private String opening_crawl;

    @Schema(description = "Pessoa que dirigiu o filme")
    private String director;

    @Schema(description = "Pessoas que produziram o filme")
    private String producer;

    @Schema(description = "Data de lançamento", example = "2024-04-27")
    private String release_date;

    @Setter
    @Schema(description = "Indica quantas vezes a descrição foi alterada")
    private String version;
}
