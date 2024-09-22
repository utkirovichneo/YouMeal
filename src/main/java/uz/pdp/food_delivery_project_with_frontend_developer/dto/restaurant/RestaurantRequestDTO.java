package uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurant;

import lombok.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.address.AddressRequestDTO;

@Getter
@Setter
public class RestaurantRequestDTO {
    private String name;
    private String phone;
    private AddressRequestDTO address;
}
