package com.automecfinder.basic.controller;

import com.automecfinder.basic.exception.UserValidationException;
import com.automecfinder.basic.model.User;
import com.automecfinder.basic.model.dto.UserPreNewDTO;
import com.automecfinder.basic.usercase.UserUseCase;
import com.automecfinder.basic.validation.UserValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/users")
@Api(value = "/api/v1/simian", consumes = APPLICATION_JSON_VALUE)
public class UserController {

    private UserUseCase userUseCase;
    private UserValidator userValidator;

    @ApiOperation(value = "Create pre new user")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "User created")})
    @ResponseStatus(CREATED)
    @PostMapping(value = "/pre-new", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPreNew(@RequestBody @Valid UserPreNewDTO userPreNewDTO) throws UserValidationException {

        log.info("User pre new received: ", userPreNewDTO);

        User user = userPreNewDTO.toDomain();

        userValidator.validatePreNew(user);

        return userUseCase.save(user).isPresent()
                ? new ResponseEntity<>(CREATED)
                : new ResponseEntity<>(BAD_REQUEST);
    }

}
