package uz.pdp.food_delivery_project_with_frontend_developer.dto.seller;

import lombok.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.user.UserResponseDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.enums.SellerStatus;

@Getter
@Setter
public class SellerDTO {
    private Long id;
    private UserResponseDTO user;
    private SellerStatus status;
}
