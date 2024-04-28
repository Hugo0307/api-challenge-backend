package com.java.spring.apichallengebackend.resource.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

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
