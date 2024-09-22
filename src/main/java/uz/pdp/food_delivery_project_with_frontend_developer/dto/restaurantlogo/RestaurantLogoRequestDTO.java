package uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurantlogo;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RestaurantLogoRequestDTO {
    private Long restaurantId;
    private MultipartFile logo;
}
