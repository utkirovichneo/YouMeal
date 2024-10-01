package uz.pdp.food_delivery_project_with_frontend_developer.dto.productpicture;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductPictureRequestDTO {
    private Long productId;
    private Long restaurantId;
}