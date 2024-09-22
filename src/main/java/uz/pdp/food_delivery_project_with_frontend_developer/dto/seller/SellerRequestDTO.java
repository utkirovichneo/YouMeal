package uz.pdp.food_delivery_project_with_frontend_developer.dto.seller;

import lombok.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.user.UserRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.enums.SellerStatus;

@Getter
@Setter
public class SellerRequestDTO {
    private UserRequestDTO user;
    private SellerStatus status;
}
