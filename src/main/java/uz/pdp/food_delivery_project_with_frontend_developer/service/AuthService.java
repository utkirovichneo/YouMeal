package uz.pdp.food_delivery_project_with_frontend_developer.service;

import uz.pdp.food_delivery_project_with_frontend_developer.dto.token.RefreshTokenRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.token.RefreshTokenResponseDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.user.UserRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.user.UserResponseDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthResponseDTO;

public interface AuthService {

    AuthResponseDTO login(UserRequestDTO userRequestDTO);

    UserResponseDTO register(UserRequestDTO userRequestDTO);

    RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);

}