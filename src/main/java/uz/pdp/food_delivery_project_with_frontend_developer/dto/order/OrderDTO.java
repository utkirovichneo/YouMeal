package uz.pdp.food_delivery_project_with_frontend_developer.dto.order;

import lombok.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.orderitem.OrderItemDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.enums.OrderStatus;

import java.util.Set;

@Getter
@Setter
public class OrderDTO {
    private Long id;
    private Long customerId;
    private Set<OrderItemDTO> orderItems;
    private double totalPrice;
    private OrderStatus status;
}