package uz.pdp.food_delivery_project_with_frontend_developer.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthRequestDTO {
    private String phoneNumber;
    private String password;
}
