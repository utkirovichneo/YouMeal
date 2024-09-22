package uz.pdp.food_delivery_project_with_frontend_developer.dto.orderitem;

import lombok.*;

@Getter
@Setter
public class OrderItemRequestDTO {

    private Long productId;

    private int quantity;

}