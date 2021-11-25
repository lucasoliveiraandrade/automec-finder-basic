package com.automecfinder.basic.model.dto;

import com.automecfinder.basic.model.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class UserPreNewDTO {

    @NotEmpty(message = "User name is required")
    private String name;

    @NotEmpty(message = "User last name is required")
    private String lastName;

    @Email(message = "User email must be valid")
    private String email;

    @NotEmpty(message = "User password is required")
    private String password; // chega criptografada

    public User toDomain() {
        return User.builder()
                .name(this.name)
                .lastName(this.lastName)
                .email(this.email)
                .password(this.password)
                .build();
    }
}
