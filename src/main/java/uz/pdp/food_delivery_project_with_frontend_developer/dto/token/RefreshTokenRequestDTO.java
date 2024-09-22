package uz.pdp.food_delivery_project_with_frontend_developer.dto.token;

import lombok.*;

@Getter
@Setter
@Builder
public class RefreshTokenRequestDTO {
    private String refreshToken;
}