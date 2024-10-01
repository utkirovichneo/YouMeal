package uz.pdp.food_delivery_project_with_frontend_developer.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.product.ProductDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.product.ProductRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.productpicture.ProductPictureDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.productpicture.ProductPictureRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.producttype.ProductTypeDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.producttype.ProductTypeRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.service.ProductService;
import uz.pdp.food_delivery_project_with_frontend_developer.util.PageableRequest;
import uz.pdp.food_delivery_project_with_frontend_developer.util.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public record ProductController(ProductService service) {


    @PostMapping("/product-type/create")
    @Operation(summary = "Bu productning typelarini yaratish. Masalan: Burger, Hamburger and other")
    public ResponseEntity<ResponseDTO<ProductTypeDTO>> create(@RequestBody ProductTypeRequestDTO dto) {
        return ResponseDTO.ok(service.create(dto));
    }

    @PostMapping("/product-type/filter")
    @Operation(summary = "Bu mavjud barcha product typelarni qaytaradi")
    public ResponseEntity<ResponseDTO<List<ProductTypeDTO>>> filter(@RequestBody PageableRequest dto) {
        return ResponseDTO.page(service.getAllType(dto));
    }

    @GetMapping("/get/{restaurantId}/{productId}")
    @Operation(summary = "Bu restaranga tegishli productni get qiladi")
    public ResponseEntity<ResponseDTO<ProductDTO>> product(@PathVariable("productId") Long productId,
                                                           @PathVariable("restaurantId") Long restaurantId){
        return ResponseDTO.ok(service.get(productId, restaurantId));
    }

    @PostMapping("/create/{restaurantId}")
    @Operation(summary = "Restaranga tegishli productlarni create qiladi")
    public ResponseEntity<ResponseDTO<ProductDTO>> create(@RequestBody ProductRequestDTO dto,
                                                          @PathVariable("restaurantId") Long restaurantId){
        return ResponseDTO.ok(service.create(dto, restaurantId));
    }

    @PutMapping("/update/{restaurantId}/{productId}")
    @Operation(summary = "Mavjud productni update qiladi")
    public ResponseEntity<ResponseDTO<ProductDTO>> update(@PathVariable("restaurantId") Long restaurantId,
                                                          @PathVariable("productId") Long productId,
                                                          @RequestBody ProductRequestDTO dto){
        return ResponseDTO.ok(service.update(dto, restaurantId, productId));
    }

    @DeleteMapping("/delete/{productId}/{restaurantId}")
    @Operation(summary = "Productni delete qiladi")
    public ResponseEntity<ResponseDTO<Boolean>> delete(@PathVariable("restaurantId") Long restaurantId,
                                                       @PathVariable("productId") Long productId){
        return ResponseDTO.ok(service.delete(productId, restaurantId));
    }

    @PostMapping("/get/all/by/{restaurantId}")
    @Operation(summary = "Restaranga tegishli barcha productlarni chiqaradi")
    public ResponseEntity<ResponseDTO<List<ProductDTO>>> getAllByRestaurant(@PathVariable("restaurantId") Long restaurantId,
                                                                            @RequestBody PageableRequest pageable){
        return ResponseDTO.page(service.getAllByRestaurant(restaurantId, pageable));
    }

    @PostMapping("/get/all")
    @Operation(summary = "Bu bazada mavjud barcha productlarni qaytaradi")
    public ResponseEntity<ResponseDTO<List<ProductDTO>>> getAll(@RequestBody PageableRequest pageable){
        return ResponseDTO.page(service.getAll(pageable));
    }

    @GetMapping("/picture/get/{restaurantId}/{productId}")
    @Operation(summary = "Bu productni rasmini qaytaradi")
    public ResponseEntity<ResponseDTO<String>> getPicture(@PathVariable("productId") Long productId,
                                                                  @PathVariable("restaurantId") Long restaurantId){
        return ResponseDTO.ok(service.url(productId, restaurantId));
    }

    @PostMapping(value = "/picture/create/",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Productga rasm biriktiriladi")
    public ResponseEntity<ResponseDTO<ProductPictureDTO>> createPicture(@RequestPart ProductPictureRequestDTO dto,
                                                                        @ModelAttribute MultipartFile logo){
        return ResponseDTO.ok(service.upload(dto.getProductId(), dto.getRestaurantId(), logo));
    }

    @PutMapping(value = "/picture/update/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Product rasmi update bo'ladi")
    public ResponseEntity<ResponseDTO<ProductPictureDTO>> updatePicture(@RequestPart ProductPictureRequestDTO dto,
                                                                        @ModelAttribute MultipartFile logo){
        return ResponseDTO.ok(service.update(dto.getProductId(), dto.getRestaurantId(), logo));
    }
}