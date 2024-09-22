package uz.pdp.food_delivery_project_with_frontend_developer.service;

import org.springframework.data.domain.Page;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.customer.CustomerDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.customer.CustomerRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.order.OrderDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthResponseDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.PageableRequest;

public interface CustomerService {

    CustomerDTO register(CustomerRequestDTO dto);

    AuthResponseDTO login(AuthRequestDTO dto);

    Page<OrderDTO> getOrders(PageableRequest pageable, Long customerId);

    Boolean payment(Long customerId, Long orderId);

}