package com.automecfinder.basic.validation;

import com.automecfinder.basic.exception.UserValidationException;
import com.automecfinder.basic.model.User;
import com.automecfinder.basic.usercase.UserUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.automecfinder.basic.enums.UserStatus.*;
import static com.automecfinder.basic.enums.ValidationMessages.*;
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
            log.info(USER_CAN_NOT_BE_NULL.toString());
            errorMessages.add(USER_CAN_NOT_BE_NULL.toString());
        }

        userUseCase.findByEmail(user.getEmail())
                .ifPresent(userDB -> {
                    if (userDB.getStatus().equals(PENDING)) {
                        log.info(EMAIL_ALREADY_USED_STATUS_PENDING.toString());
                        errorMessages.add(EMAIL_ALREADY_USED_STATUS_PENDING.toString());
                    } else if (userDB.getStatus().equals(ACTIVE)) {
                        log.info(EMAIL_ALREADY_USED_STATUS_ACTIVE.toString());
                        errorMessages.add(EMAIL_ALREADY_USED_STATUS_ACTIVE.toString());
                    } else if (userDB.getStatus().equals(INACTIVE)) {
                        log.info(EMAIL_ALREADY_USED_STATUS_INACTIVE.toString());
                        errorMessages.add(EMAIL_ALREADY_USED_STATUS_INACTIVE.toString());
                    }
                });

        if (isNotEmpty(errorMessages)) {
            throw new UserValidationException(errorMessages);
        }
    }
}
