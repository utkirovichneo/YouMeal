package uz.pdp.food_delivery_project_with_frontend_developer.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.food_delivery_project_with_frontend_developer.config.service.CustomUserDetailsService;
import uz.pdp.food_delivery_project_with_frontend_developer.config.service.JWTService;

import java.io.IOException;

@Component
@Slf4j
public class JWTTokenFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public JWTTokenFilter(JWTService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || authorization.isBlank()){
            filterChain.doFilter(request, response);
            return;
        }
        /*Bu joyida Bearer_ dan keyingisi token bo'ladi shuni kesib oldik*/
        String token = authorization.substring(7);

        if(!jwtService.isValid(token)){
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtService.getUsername(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}