package com.java.spring.apichallengebackend.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateDescriptionRequest {

    @NotBlank(message = "Informação obrigatória!")
    @Size(min = 1, max = 600)
    @Schema(requiredMode = RequiredMode.REQUIRED,
            description = "Texto da nova descrição")
    private String description;

}
