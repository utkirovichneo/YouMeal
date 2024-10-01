package uz.pdp.food_delivery_project_with_frontend_developer.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurant.RestaurantDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurant.RestaurantNameUpdateDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurant.RestaurantRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurantlogo.RestaurantLogoDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurantlogo.RestaurantLogoRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.seller.SellerDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.seller.SellerRequestDTO;
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
    @Operation(summary = "Sellerlarni ro'yxatga olish")
    public ResponseEntity<ResponseDTO<SellerDTO>> register(@RequestBody SellerRequestDTO dto) {
        return ResponseDTO.ok(service.register(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "Sellerlar login qiladi")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> login(@RequestBody AuthRequestDTO dto) {
        return ResponseDTO.ok(service.login(dto));
    }

    @GetMapping("/restaurant/get/{sellerId}/{restaurantId}")
    @Operation(summary = "Sellerning restaranini get qiladi")
    public ResponseEntity<ResponseDTO<RestaurantDTO>> getRestaurant(@PathVariable("restaurantId") Long restaurantId,
                                                                    @PathVariable("sellerId") Long sellerId) {
        return ResponseDTO.ok(service.getRestaurant(sellerId, restaurantId));
    }

    @PostMapping("/restaurant/create/{sellerId}")
    @Operation(summary = "Seller o'ziga restaran ochadi. Masalan: Evos, Oqtepa ...")
    public ResponseEntity<ResponseDTO<RestaurantDTO>> createRestaurant(@RequestBody RestaurantRequestDTO dto,
                                                                       @PathVariable("sellerId") Long sellerId) {
        return ResponseDTO.ok(service.createRestaurant(sellerId, dto));
    }

    @PutMapping("/restaurant/update/{sellerId}/{restaurantId}")
    @Operation(summary = "Seller restaranni update qiladi")
    public ResponseEntity<ResponseDTO<RestaurantDTO>> updateRestaurant(@RequestBody RestaurantNameUpdateDTO dto,
                                                                       @PathVariable("restaurantId") Long restaurantId,
                                                                       @PathVariable("sellerId") Long sellerId) {
        return ResponseDTO.ok(service.updateRestaurant(sellerId, restaurantId, dto));
    }

    @PostMapping("/restaurant/filter/{sellerId}")
    @Operation(summary = "Sellerga tegishli barcha restaranlarni qaytaradi")
    public ResponseEntity<ResponseDTO<List<RestaurantDTO>>> getAllRestaurants(@PathVariable("sellerId") Long sellerId,
                                                                              @RequestBody PageableRequest pageable) {
        return ResponseDTO.page(service.getSellerRestaurants(sellerId, pageable));
    }

    @PostMapping("/restaurant/filter")
    @Operation(summary = "Bu dasturda mavjud bo'lgan barcha restaranlarni qaytaradi. Bu qismi customer dashboardda foydalaniladi")
    public ResponseEntity<ResponseDTO<List<RestaurantDTO>>> getAllRestaurants(@RequestBody PageableRequest pageable) {
        return ResponseDTO.page(service.getRestaurants(pageable));
    }

    @GetMapping("/restaurant/logo/{restaurantId}")
    @Operation(summary = "Bu restaranga tegishli logotipni AWS dan url ini olib keladi")
    public ResponseEntity<ResponseDTO<String>> getRestaurantLogo(@PathVariable Long restaurantId) {
        return ResponseDTO.ok(service.url(restaurantId));
    }

    @PostMapping(value = "/restaurant/logo/", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Restaranga logo picture add qilish, bitta restaran bitta logo")
    public ResponseEntity<ResponseDTO<RestaurantLogoDTO>> restaurantLogo(@RequestParam("logo") MultipartFile logo,
                                                                         @ModelAttribute RestaurantLogoRequestDTO dto
                                                                         ) {
        return ResponseDTO.ok(service().upload(dto.getSellerId(), dto.getRestaurantId(), logo));
    }

    @PutMapping(value = "/restaurant/logo/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Restaran logosini update qilish")
    public ResponseEntity<ResponseDTO<RestaurantLogoDTO>> updateRestaurantLogo(@RequestParam("logo") MultipartFile logo,
                                                                               @ModelAttribute RestaurantLogoRequestDTO dto
                                                                               ) {
        return ResponseDTO.ok(service.update(dto.getSellerId(), dto.getRestaurantId(), logo));
    }

    @PostMapping("/restaurant/all")
    @Operation(summary = "Bu barcha restaranlarni page larini qaytaradi")
    public ResponseEntity<ResponseDTO<List<RestaurantDTO>>> getAll(@RequestBody PageableRequest dto){
        return ResponseDTO.page(service.getRestaurants(dto));
    }
}