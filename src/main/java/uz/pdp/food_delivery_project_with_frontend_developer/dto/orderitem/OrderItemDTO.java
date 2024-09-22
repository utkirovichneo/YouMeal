package uz.pdp.food_delivery_project_with_frontend_developer.dto.orderitem;

import lombok.*;

@Getter
@Setter
public class OrderItemDTO {
    private Long id;
    private Long productId;
    private String productName;
    private String quantity;
    private double totalAmount;
}