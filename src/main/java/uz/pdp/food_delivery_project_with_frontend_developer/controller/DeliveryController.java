package uz.pdp.food_delivery_project_with_frontend_developer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.deliveryperson.DeliveryPersonDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.deliveryperson.DeliveryPersonRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.order.OrderDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.packagedto.PackageDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.service.DeliveryService;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthResponseDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.PageableRequest;
import uz.pdp.food_delivery_project_with_frontend_developer.util.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public record DeliveryController(DeliveryService service) {

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> login(@RequestParam AuthRequestDTO dto){
        return ResponseDTO.ok(service.login(dto));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<DeliveryPersonDTO>> register(@RequestParam DeliveryPersonRequestDTO dto){
        return ResponseDTO.ok(service.register(dto));
    }

    @GetMapping("/order/list")
    public ResponseEntity<ResponseDTO<List<OrderDTO>>> getOrderList(@RequestBody PageableRequest pageable){
        return ResponseDTO.page(service.orderList(pageable));
    }

    @PostMapping("/order/confirm/{orderId}/{deliverId}")
    public ResponseEntity<ResponseDTO<Boolean>> confirm(@PathVariable("orderId") Long orderId,
                                                        @PathVariable("deliverId") Long deliverId){
        return ResponseDTO.ok(service.confirm(orderId, deliverId));
    }

    @PostMapping("order/close/{orderId}/{deliverId}")
    public ResponseEntity<ResponseDTO<Boolean>> close(@PathVariable("deliverId") Long deliverId,
                                                      @PathVariable("orderId") Long orderId){
        return ResponseDTO.ok(service.close(orderId, deliverId));
    }

    @GetMapping("order/my/{deliverId}")
    public ResponseEntity<ResponseDTO<List<PackageDTO>>> getMyOrder(@PathVariable("deliverId") Long deliverId,
                                                                    @RequestBody PageableRequest pageable){
        return ResponseDTO.page(service.myPackage(deliverId, pageable));
    }
}