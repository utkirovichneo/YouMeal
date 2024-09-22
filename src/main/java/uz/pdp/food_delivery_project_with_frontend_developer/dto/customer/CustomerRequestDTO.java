package uz.pdp.food_delivery_project_with_frontend_developer.dto.customer;

import lombok.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.address.AddressRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.user.UserRequestDTO;

@Getter
@Setter
public class CustomerRequestDTO {

    private UserRequestDTO user;

    private AddressRequestDTO address;

}