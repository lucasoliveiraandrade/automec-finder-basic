package com.automecfinder.basic.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import static java.util.Arrays.asList;

@Getter
@NoArgsConstructor
public class ErrorResponseDTO implements Serializable {

    private static final long serialVersionUID = 4435959686991135330L;

    private List<String> errors;

    public ErrorResponseDTO(String errorMessage) {
        this.errors = asList(errorMessage);
    }

    public ErrorResponseDTO(List<String> errorMessages) {
        this.errors = errorMessages;
    }
}
