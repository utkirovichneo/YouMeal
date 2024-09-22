package uz.pdp.food_delivery_project_with_frontend_developer.service;

import org.springframework.data.domain.Page;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.deliveryperson.DeliveryPersonDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.deliveryperson.DeliveryPersonRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.order.OrderDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.packagedto.PackageDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthResponseDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.PageableRequest;

public interface DeliveryService {

    DeliveryPersonDTO register(DeliveryPersonRequestDTO deliveryPersonDTO);

    AuthResponseDTO login(AuthRequestDTO dto);

    Page<OrderDTO> orderList(PageableRequest pageable);

    Boolean confirm(Long orderId, Long deliverId);

    Boolean close(Long orderId, Long deliverId);

    Page<PackageDTO> myPackage(Long deliverId, PageableRequest pageable);
}
