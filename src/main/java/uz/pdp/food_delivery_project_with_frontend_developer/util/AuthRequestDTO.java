package uz.pdp.food_delivery_project_with_frontend_developer.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthRequestDTO {
    @Schema(defaultValue = "+998901234567")
    private String phoneNumber;
    private String password;
}
