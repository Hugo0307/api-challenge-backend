package com.java.spring.apichallengebackend.exception;

import org.springframework.http.HttpStatus;

import com.java.spring.apichallengebackend.enums.ChallengeTypeErrorEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChallengeCustomException extends RuntimeException{

    private ChallengeTypeErrorEnum error;
    private HttpStatus httpStatus;
    private String message;

    public ChallengeCustomException(ChallengeTypeErrorEnum enumError, HttpStatus httpStatus, String message) {
        super(message);
        this.error = enumError;
        this.httpStatus = httpStatus;
        this.message = message;
    }

}
