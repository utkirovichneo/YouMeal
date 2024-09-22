package uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurant;

import lombok.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.address.AddressDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.seller.SellerDTO;

@Getter
@Setter
public class RestaurantDTO {
    private Long id;
    private String name;
    private String phone;
    private AddressDTO address;
    private SellerDTO seller;
}