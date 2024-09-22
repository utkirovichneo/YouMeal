package uz.pdp.food_delivery_project_with_frontend_developer.dto.address;

import lombok.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.location.LocationRequestDTO;

@Getter
@Setter
public class AddressRequestDTO {

    private String street;
    private String apartment;

    private LocationRequestDTO location;
}