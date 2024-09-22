package uz.pdp.food_delivery_project_with_frontend_developer.service;

import uz.pdp.food_delivery_project_with_frontend_developer.dto.order.OrderDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.orderitem.OrderItemDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.orderitem.OrderItemRequestDTO;

public interface OrderService {

    OrderItemDTO add(Long customerId, OrderItemRequestDTO dto);

    OrderItemDTO update(Long customerId, Long orderItemId, OrderItemRequestDTO dto);

    OrderItemDTO get(Long customerId, Long orderItemId);

    Boolean delete(Long customerId, Long orderItemId);

    OrderDTO get(Long customerId);

    Boolean confirm(Long customerId);

    Boolean reject(Long customerId);

}
