package com.automecfinder.basic.config.advice;

import com.automecfinder.basic.exception.UserValidationException;
import com.automecfinder.basic.model.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationAdvice {

    @ExceptionHandler(UserValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO handleUserValidationException(UserValidationException e) {
        return new ErrorResponseDTO(e.getMessage());
    }
}
