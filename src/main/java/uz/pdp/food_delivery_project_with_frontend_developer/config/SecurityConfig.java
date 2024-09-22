package uz.pdp.food_delivery_project_with_frontend_developer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletOutputStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import uz.pdp.food_delivery_project_with_frontend_developer.config.service.CustomUserDetailsService;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.error.ErrorResDTO;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String [] WHITE_LIST = new String[]{
            "/api/auth/login",
            "/api/auth/register",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"};
    private final ObjectMapper objectMapper;
    private final JWTTokenFilter jwtTokenFilter;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(ObjectMapper objectMapper, JWTTokenFilter jwtTokenFilter, CustomUserDetailsService customUserDetailsService) {
        this.objectMapper = objectMapper;
        this.jwtTokenFilter = jwtTokenFilter;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);

        http.sessionManagement(sessionManagement ->
                sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers(WHITE_LIST)
                        .permitAll()
                        .anyRequest()
                        .fullyAuthenticated());

        http.exceptionHandling(exceptions ->
                exceptions.authenticationEntryPoint(
                        (request, response, exception) -> {
                            String requestURI = request.getRequestURI();
                            String message = exception.getMessage();
                            int errorCode = 401;
                            ErrorResDTO errorResDTO = new ErrorResDTO(errorCode, requestURI, message);
                            response.setStatus(errorCode);
                            ServletOutputStream outputStream = response.getOutputStream();
                            objectMapper.writeValue(outputStream, errorResDTO);
                        }
                ));

        http.authenticationProvider(authenticationProvider())
                        .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOriginPatterns(List.of("*"));
                configuration.setAllowedHeaders(List.of("*"));
                    configuration.setAllowedMethods(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
                return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(WHITE_LIST); // Swagger static resources
    }

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

}