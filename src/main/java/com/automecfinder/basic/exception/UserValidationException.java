package com.automecfinder.basic.exception;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

public class UserValidationException extends Exception {

    public UserValidationException(String errorMessage) {
        super(errorMessage);
    }

    public UserValidationException(List<String> errorMessages) {
        this(emptyIfNull(errorMessages).stream().collect(joining(", ")));
    }

}
