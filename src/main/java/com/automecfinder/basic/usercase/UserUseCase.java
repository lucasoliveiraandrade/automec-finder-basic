package com.automecfinder.basic.usercase;

import com.automecfinder.basic.model.User;
import com.automecfinder.basic.repository.UserRepository;
import com.automecfinder.basic.util.ActivationTokenUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.automecfinder.basic.enums.UserStatus.PENDING;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.*;

@Service
@Slf4j
@AllArgsConstructor
public class UserUseCase implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return ofNullable(userRepository.findByEmail(defaultIfEmpty(email, EMPTY)))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email " + email));
    }

    public Optional<User> findByEmail(String email) {
        return isBlank(email) ? empty() : ofNullable(userRepository.findByEmail(email));
    }

    public Optional<User> savePreNew(User user) {
        user.setActivationToken(ActivationTokenUtil.getNew());
        user.setStatus(PENDING);
        return ofNullable(userRepository.save(user));
    }

}
