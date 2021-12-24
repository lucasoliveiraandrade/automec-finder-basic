package com.automecfinder.basic.config.security;

import com.automecfinder.basic.usercase.UserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.automecfinder.basic.enums.UserRoles.ADMIN;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserUseCase userUseCase;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        // validando usuarios carregados em memória
        auth.inMemoryAuthentication()
                .withUser("lucas.andrade999@gmail.com")
                .password(passwordEncoder.encode("lucas"))
                .roles(ADMIN.name());

        // validando usuarios carregados da base dados
        auth.userDetailsService(userUseCase)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable();
    }
}
