package uz.pdp.food_delivery_project_with_frontend_developer.dto.packagedto;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.order.OrderDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.DeliveryPerson;

@Getter
@Setter
public class PackageDTO {
    private OrderDTO order;
    private DeliveryPerson person;
}
