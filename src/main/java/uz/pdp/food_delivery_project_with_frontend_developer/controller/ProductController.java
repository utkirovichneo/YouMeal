package uz.pdp.food_delivery_project_with_frontend_developer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.product.ProductDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.product.ProductRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.productpicture.ProductPictureDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.productpicture.ProductPictureRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.service.ProductService;
import uz.pdp.food_delivery_project_with_frontend_developer.util.PageableRequest;
import uz.pdp.food_delivery_project_with_frontend_developer.util.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public record ProductController(ProductService service) {

    @GetMapping("/get/{restaurantId}/{productId}")
    public ResponseEntity<ResponseDTO<ProductDTO>> product(@PathVariable("productId") Long productId,
                                                           @PathVariable("restaurantId") Long restaurantId){
        return ResponseDTO.ok(service.get(productId, restaurantId));
    }

    @PostMapping("/create/{restaurantId}")
    public ResponseEntity<ResponseDTO<ProductDTO>> create(@RequestBody ProductRequestDTO dto,
                                                          @PathVariable("restaurantId") Long restaurantId){
        return ResponseDTO.ok(service.create(dto, restaurantId));
    }

    @PutMapping("/update/{restaurantId}/{productId}")
    public ResponseEntity<ResponseDTO<ProductDTO>> update(@PathVariable("restaurantId") Long restaurantId,
                                                          @PathVariable("productId") Long productId,
                                                          @RequestBody ProductRequestDTO dto){
        return ResponseDTO.ok(service.update(dto, restaurantId, productId));
    }

    @DeleteMapping("/delete/{productId}/{restaurantId}")
    public ResponseEntity<ResponseDTO<Boolean>> delete(@PathVariable("restaurantId") Long restaurantId,
                                                       @PathVariable("productId") Long productId){
        return ResponseDTO.ok(service.delete(productId, restaurantId));
    }

    @PostMapping("/get/all/by/{restaurantId}")
    public ResponseEntity<ResponseDTO<List<ProductDTO>>> getAllByRestaurant(@PathVariable("restaurantId") Long restaurantId,
                                                                            @RequestBody PageableRequest pageable){
        return ResponseDTO.page(service.getAllByRestaurant(restaurantId, pageable));
    }

    @PostMapping("/get/all")
    public ResponseEntity<ResponseDTO<List<ProductDTO>>> getAll(@RequestBody PageableRequest pageable){
        return ResponseDTO.page(service.getAll(pageable));
    }

    // TODO
    @GetMapping("/picture/get/{restaurantId}/{productId}")
    public ResponseEntity<ResponseDTO<ProductPictureDTO>> getPicture(@PathVariable("productId") Long productId,
                                                                  @PathVariable("restaurantId") Long restaurantId){
        return ResponseDTO.ok();
    }

    // TODO
    @PostMapping("/picture/create/{restaurantId}/{productId}")
    public ResponseEntity<ResponseDTO<ProductPictureDTO>> createPicture(@RequestBody ProductPictureRequestDTO dto,
                                                                  @PathVariable("productId") Long productId,
                                                                  @PathVariable("restaurantId") Long restaurantId){
        return ResponseDTO.ok();
    }

    // TODO
    @PutMapping("/picture/update/{restaurantId}/{productId}")
    public ResponseEntity<ResponseDTO<ProductPictureDTO>> updatePicture(@RequestBody ProductPictureRequestDTO dto,
                                                                  @PathVariable("productId") Long productId,
                                                                  @PathVariable("restaurantId") Long restaurantId){
        return ResponseDTO.ok();
    }
}