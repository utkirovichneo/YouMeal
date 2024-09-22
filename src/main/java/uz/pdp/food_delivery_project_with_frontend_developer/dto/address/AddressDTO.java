package uz.pdp.food_delivery_project_with_frontend_developer.dto.address;

import lombok.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.location.LocationDTO;

@Getter
@Setter
public class AddressDTO {

    private Long id;
    private String street;
    private String apartment;

    private LocationDTO location;
}