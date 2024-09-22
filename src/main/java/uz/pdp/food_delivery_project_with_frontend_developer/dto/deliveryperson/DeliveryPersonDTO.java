package uz.pdp.food_delivery_project_with_frontend_developer.dto.deliveryperson;

import lombok.*;
import uz.pdp.food_delivery_project_with_frontend_developer.enums.VehicleType;

@Getter
@Setter
public class DeliveryPersonDTO {
    private Long id;
    private VehicleType vehicleType;
}