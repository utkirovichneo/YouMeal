package uz.pdp.food_delivery_project_with_frontend_developer.service;

import org.springframework.data.domain.Page;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurant.RestaurantDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurant.RestaurantRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.seller.SellerDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.seller.SellerRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthResponseDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.PageableRequest;

public interface SellerService {

    SellerDTO register(SellerRequestDTO dto);

    AuthResponseDTO login(AuthRequestDTO dto);

    RestaurantDTO createRestaurant(Long sellerId, RestaurantRequestDTO dto);

    RestaurantDTO getRestaurant(Long sellerId, Long restaurantId);

    Page<RestaurantDTO> getSellerRestaurants(Long sellerId, PageableRequest pageable);

    Page<RestaurantDTO> getRestaurants(PageableRequest pageable);

}