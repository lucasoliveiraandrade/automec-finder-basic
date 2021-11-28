package com.automecfinder.basic.usercase;

import com.automecfinder.basic.config.kafka.KafkaSender;
import com.automecfinder.basic.enums.UserRoles;
import com.automecfinder.basic.exception.ActivationTokenNotFoundException;
import com.automecfinder.basic.model.User;
import com.automecfinder.basic.repository.UserRepository;
import com.automecfinder.basic.util.ActivationTokenUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static com.automecfinder.basic.enums.UserRoles.USER;
import static com.automecfinder.basic.enums.UserStatus.ACTIVE;
import static com.automecfinder.basic.enums.UserStatus.PENDING;
import static com.automecfinder.basic.enums.ValidationMessages.ACTIVATION_TOKEN_NOT_FOUND;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.*;

@Service
@Slf4j
@AllArgsConstructor
public class UserUseCase implements UserDetailsService {

    private UserRepository userRepository;
    private KafkaSender kafkaSender;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return ofNullable(userRepository.findByEmail(defaultIfEmpty(email, EMPTY)))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email " + email));
    }

    public Optional<User> findByEmail(String email) {
        return isBlank(email) ? empty() : ofNullable(userRepository.findByEmail(email));
    }

    public void savePreNew(User user) throws Exception {
        user.setActivationToken(ActivationTokenUtil.getNew());
        user.setStatus(PENDING);
        user.setRoles(USER.toString());

        try {
            User save = userRepository.save(user);
            log.info("New user {} saved", save.getId());
            kafkaSender.enqueue(save);
        } catch (Exception e) {
            throw new Exception("The following problem occurred on saving new user:\n", e);
        }
    }

    public void activeUser(String token) throws ActivationTokenNotFoundException {
        ofNullable(userRepository.findByActivationToken(token))
                .filter(User::isEnabled)
                .map(user -> {
                    user.setActivationAt(LocalDate.now());
                    user.setActivationToken(null);
                    user.setStatus(ACTIVE);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ActivationTokenNotFoundException(ACTIVATION_TOKEN_NOT_FOUND.toString()));
    }
}
