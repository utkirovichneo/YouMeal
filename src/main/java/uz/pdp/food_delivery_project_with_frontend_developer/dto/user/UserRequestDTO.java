package uz.pdp.food_delivery_project_with_frontend_developer.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
public class UserRequestDTO {
    private String phoneNumber;
    private String password;
}