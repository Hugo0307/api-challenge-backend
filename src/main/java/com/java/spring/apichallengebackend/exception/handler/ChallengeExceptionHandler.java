package com.java.spring.apichallengebackend.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.java.spring.apichallengebackend.enums.ChallengeTypeErrorEnum;
import com.java.spring.apichallengebackend.exception.ChallengeCustomException;
import com.java.spring.apichallengebackend.exception.ErrorResponse;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@ControllerAdvice
public class ChallengeExceptionHandler {

    @ExceptionHandler(ChallengeCustomException.class)
    public ResponseEntity<ErrorResponse> challengeCustomExceptionHandler(ChallengeCustomException e) {
        ErrorResponse errorResponse = buildErrorResponse(
            e.getError().getTitle(), 
            e.getError().getStatus(), 
            e.getMessage()
        );
        return ResponseEntity.status(e.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        ErrorResponse errorResponse = buildErrorResponse(
            ChallengeTypeErrorEnum.INVALID_ARGUMENT.getTitle(), 
            ChallengeTypeErrorEnum.INVALID_ARGUMENT.getStatus(), 
            "Argument type is invalid!"
        );
        log.error("Error: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception e) {
        ErrorResponse errorResponse = buildErrorResponse(
            ChallengeTypeErrorEnum.INTERNAL_ERROR.getTitle(), 
            ChallengeTypeErrorEnum.INTERNAL_ERROR.getStatus(), 
            "Operation internal error."
        );
        log.error("Internal Error: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    private ErrorResponse buildErrorResponse(String title, String statusCode, String message) {
        return ErrorResponse
            .builder()
            .title(title)
            .status(statusCode)
            .detail(message)
            .timestamp(LocalDateTime.now())
            .build();
    }

}
