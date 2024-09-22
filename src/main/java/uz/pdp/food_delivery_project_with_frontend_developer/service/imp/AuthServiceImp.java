package uz.pdp.food_delivery_project_with_frontend_developer.service.imp;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.food_delivery_project_with_frontend_developer.config.service.JWTService;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.token.RefreshTokenRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.token.RefreshTokenResponseDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.user.UserRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.user.UserResponseDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.User;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.UserMapper;
import uz.pdp.food_delivery_project_with_frontend_developer.repository.UserRepository;
import uz.pdp.food_delivery_project_with_frontend_developer.service.AuthService;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthResponseDTO;

import java.util.Set;

@Service
public class AuthServiceImp implements AuthService {

    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImp(UserMapper userMapper, AuthenticationManager authenticationManager, JWTService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponseDTO login(UserRequestDTO userRequestDTO) {

        var user = userRepository.findUserByPhoneNumber(userRequestDTO.getPhoneNumber())
                .orElseThrow(() -> new UsernameNotFoundException("Bunday raqamli USER mavjud emas"));

        if(!passwordEncoder.matches(userRequestDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password doesn't match");
        }

        var authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userRequestDTO.getPhoneNumber(),
                userRequestDTO.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return AuthResponseDTO
                .builder()
                .accessToken(jwtService.accessToken(userRequestDTO.getPhoneNumber()))
                .refreshToken(jwtService.refreshToken(userRequestDTO.getPhoneNumber()))
                .build();
    }

    @Override
    public UserResponseDTO register(UserRequestDTO userRequestDTO) {

        var user = userRepository.save(User
                .builder()
                .phoneNumber(userRequestDTO.getPhoneNumber())
                .password(passwordEncoder.encode(userRequestDTO.getPassword()))
                .roles(Set.of())
                .build());

        return userMapper.toDto(user);
    }

    @Override
    public RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        var refreshToken = refreshTokenRequestDTO.getRefreshToken();
        var phoneNumber = jwtService.getUsername(refreshToken);
        return RefreshTokenResponseDTO.builder()
                .accessToken(jwtService.accessToken(phoneNumber))
                .build();
    }
}