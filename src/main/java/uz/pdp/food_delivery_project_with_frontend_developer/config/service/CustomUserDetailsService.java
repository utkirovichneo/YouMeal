package uz.pdp.food_delivery_project_with_frontend_developer.config.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.food_delivery_project_with_frontend_developer.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository
                .findUserByPhoneNumber(username)
                .orElseThrow(
                        ()-> new UsernameNotFoundException(username)
                );
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}