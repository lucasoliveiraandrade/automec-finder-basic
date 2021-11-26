package com.automecfinder.basic.config.advice;

import com.automecfinder.basic.exception.ActivationTokenNotFoundException;
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

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDTO handleServerErrorException(Exception e) {
        return new ErrorResponseDTO(e.getMessage());
    }


    @ExceptionHandler(ActivationTokenNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO handleActivationTokenNotFoundException(ActivationTokenNotFoundException e) {
        return new ErrorResponseDTO(e.getMessage());
    }


}
