package com.automecfinder.basic.validation;

import com.automecfinder.basic.exception.UserValidationException;
import com.automecfinder.basic.model.User;
import com.automecfinder.basic.usercase.UserUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Service
@Slf4j
@AllArgsConstructor
public class UserValidator {

    private UserUseCase userUseCase;

    public void validatePreNew(User user) throws UserValidationException {

        List<String> errorMessages = new ArrayList<>();

        if (isNull(user)) {
            log.info("User can't be null");
            errorMessages.add("User can't be null");
        }

        userUseCase.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    log.info("Email already used");
                    errorMessages.add("Email already used");
                });


        if (isNotEmpty(errorMessages)) {
            throw new UserValidationException(errorMessages);
        }
    }
}
