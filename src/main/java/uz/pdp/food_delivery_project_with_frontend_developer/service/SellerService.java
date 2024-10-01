package uz.pdp.food_delivery_project_with_frontend_developer.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurant.RestaurantDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurant.RestaurantNameUpdateDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurant.RestaurantRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurantlogo.RestaurantLogoDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurantlogo.RestaurantLogoRequestDTO;
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

    RestaurantDTO updateRestaurant(Long sellerId, Long restaurantId, RestaurantNameUpdateDTO dto);

    RestaurantLogoDTO upload(Long sellerId, Long restaurantId, MultipartFile logo);

    String url(Long restaurantId);

    RestaurantLogoDTO update(Long sellerId, Long restaurantId, MultipartFile logo);

    Page<RestaurantDTO> getAllRestaurants(PageableRequest pageable);

}