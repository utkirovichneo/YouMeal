package uz.pdp.food_delivery_project_with_frontend_developer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.product.ProductDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.product.ProductRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.productpicture.ProductPictureDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.productpicture.ProductPictureRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.service.ProductService;
import uz.pdp.food_delivery_project_with_frontend_developer.util.ResponseDTO;

@RestController
@RequestMapping("/api/product")
public record ProductController(ProductService service) {

    // TODO
    @GetMapping("/get/{restaurantId}/{productId}")
    public ResponseEntity<ResponseDTO<ProductDTO>> product(@PathVariable("productId") Long productId,
                                                           @PathVariable("restaurantId") Long restaurantId){
        return ResponseDTO.ok();
    }

    // TODO
    @PostMapping("/create/{restaurantId}")
    public ResponseEntity<ResponseDTO<ProductDTO>> create(@RequestBody ProductRequestDTO dto,
                                                          @PathVariable("restaurantId") Long restaurantId){
        return ResponseDTO.ok();
    }

    // TODO
    @PutMapping("/update/{restaurantId}")
    public ResponseEntity<ResponseDTO<ProductDTO>> update(@PathVariable("restaurantId") Long restaurantId,
                                                          @RequestBody ProductRequestDTO dto){
        return ResponseDTO.ok();
    }

    // TODO
    @DeleteMapping("/delete/{productId}/{restaurantId}")
    public ResponseEntity<ResponseDTO<Boolean>> delete(@PathVariable("restaurantId") Long restaurantId,
                                                       @PathVariable("productId") Long productId){
        return ResponseDTO.ok();
    }

    // TODO
    @GetMapping("/picture/get/{restaurantId}/{productId}")
    public ResponseEntity<ResponseDTO<ProductPictureDTO>> picture(@PathVariable("productId") Long productId,
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