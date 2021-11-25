package com.automecfinder.basic.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class ErrorResponseDTO implements Serializable {

    private static final long serialVersionUID = 4435959686991135330L;

    private String error;

    public ErrorResponseDTO(String errorMessage) {
        this.error = errorMessage;
    }
}
