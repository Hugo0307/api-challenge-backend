package com.java.spring.apichallengebackend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChallengeTypeErrorEnum {

    INVALID_ARGUMENT(400, "INVALID ARGUMENT TYPE", "BAD_REQUEST"),
    FILM_NOT_FOUND(404, "FILM NOT FOUND", "NOT_FOUND"),
    INTERNAL_ERROR(500, "INTERNAL ERROR", "INTERNAL_SERVER_ERROR");

    private Integer code;
    private String title;
    private String status;
}
