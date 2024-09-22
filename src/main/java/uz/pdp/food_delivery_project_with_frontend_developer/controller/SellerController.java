package uz.pdp.food_delivery_project_with_frontend_developer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurant.RestaurantDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurant.RestaurantRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurantlogo.RestaurantLogoDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurantlogo.RestaurantLogoRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.seller.SellerDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.seller.SellerRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.RestaurantLogo;
import uz.pdp.food_delivery_project_with_frontend_developer.service.SellerService;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthResponseDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.PageableRequest;
import uz.pdp.food_delivery_project_with_frontend_developer.util.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/seller/")
public record SellerController(SellerService service) {

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<SellerDTO>> register(@RequestBody SellerRequestDTO dto) {
        return ResponseDTO.ok(service.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> login(@RequestBody AuthRequestDTO dto) {
        return ResponseDTO.ok(service.login(dto));
    }

    @GetMapping("/restaurant/get/{sellerId}/{restaurantId}")
    public ResponseEntity<ResponseDTO<RestaurantDTO>> getRestaurant(@PathVariable("restaurantId") Long restaurantId,
                                                                    @PathVariable("sellerId") Long sellerId) {
        return ResponseDTO.ok(service.getRestaurant(sellerId, restaurantId));
    }

    @PostMapping("/restaurant/create/{sellerId}")
    public ResponseEntity<ResponseDTO<RestaurantDTO>> createRestaurant(@RequestBody RestaurantRequestDTO dto,
                                                                       @PathVariable("sellerId") Long sellerId) {
        return ResponseDTO.ok(service.createRestaurant(sellerId, dto));
    }

    // TODO
    @PutMapping("/restaurant/update/{sellerId}/{restaurantId}")
    public ResponseEntity<ResponseDTO<RestaurantDTO>> updateRestaurant(@RequestBody RestaurantRequestDTO dto,
                                                                       @PathVariable("restaurantId") Long restaurantId,
                                                                       @PathVariable("sellerId") Long sellerId) {
        return ResponseDTO.ok();
    }

    @PostMapping("/restaurant/filter/{sellerId}")
    public ResponseEntity<ResponseDTO<List<RestaurantDTO>>> getAllRestaurants(@PathVariable("sellerId") Long sellerId,
                                                                              @RequestBody PageableRequest pageable) {
        return ResponseDTO.page(service.getSellerRestaurants(sellerId, pageable));
    }

    @PostMapping("/restaurant/filter")
    public ResponseEntity<ResponseDTO<List<RestaurantDTO>>> getAllRestaurants(@RequestBody PageableRequest pageable) {
        return ResponseDTO.page(service.getRestaurants(pageable));
    }

    // TODO
    @GetMapping("/restaurant/logo/{restaurantId}")
    public ResponseEntity<ResponseDTO<RestaurantLogo>> getRestaurantLogo(@PathVariable Long restaurantId) {
        return ResponseDTO.ok();
    }

    // TODO
    @PostMapping("/restaurant/logo/{restaurantId}")
    public ResponseEntity<ResponseDTO<RestaurantLogoDTO>> restaurantLogo(@PathVariable Long restaurantId,
                                                                         @RequestBody RestaurantLogoRequestDTO dto) {
        return ResponseDTO.ok();
    }

    // TODO
    @PutMapping("/restaurant/logo/{restaurantId}")
    public ResponseEntity<ResponseDTO<RestaurantLogoDTO>> updateRestaurantLogo(@PathVariable Long restaurantId,
                                                                               @RequestBody RestaurantLogoRequestDTO dto) {
        return ResponseDTO.ok();
    }
}