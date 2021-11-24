package com.automecfinder.basic.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Collection;

@Data
@Builder
@Document(collection = "user")
public class User implements UserDetails {

    @Id
    private String id;

    @NotEmpty(message = "User name is required")
    private String name;

    @NotEmpty(message = "User last name is required")
    private String lastName;

    @Email(message = "User email must be valid")
    private String email;

    @NotEmpty(message = "User password is required")
    private String password;

    @Past(message = "User birth must be valid")
    private LocalDate birth;

    @NotEmpty(message = "User state is required")
    private String state;

    @NotEmpty(message = "User city is required")
    private String city;

    @NotEmpty(message = "User street is required")
    private String street;

    private String complement;

    @NotEmpty(message = "User cep is required")
    private Integer cep;

    private String profilePicture;

    private LocalDate firstAccess;

    private LocalDate lastAccess;

    @NotEmpty(message = "User roles is required")
    private String roles;

    @NotEmpty(message = "At least one phone number is required")
    private String firstPhoneNumber;

    private String secondPhoneNumber;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
