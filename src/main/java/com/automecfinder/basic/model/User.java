package com.automecfinder.basic.model;

import com.automecfinder.basic.enums.UserStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static java.util.stream.Collectors.toList;

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

    @NotEmpty(message = "User roles are required")
    private String roles;

    private String firstPhoneNumber;

    private String secondPhoneNumber;

    private UserStatus status;

    private String activationToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
