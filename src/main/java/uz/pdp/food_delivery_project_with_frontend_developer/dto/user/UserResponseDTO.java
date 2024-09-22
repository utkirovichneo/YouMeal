package uz.pdp.food_delivery_project_with_frontend_developer.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponseDTO {
    private String phoneNumber;
    private String password;
}