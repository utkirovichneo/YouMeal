package uz.pdp.food_delivery_project_with_frontend_developer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.deliveryperson.DeliveryPersonDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.deliveryperson.DeliveryPersonRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.order.OrderDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.service.DeliveryService;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthResponseDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public record DeliveryController(DeliveryService service) {

    // TODO
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> login(@RequestParam AuthRequestDTO dto){
        return ResponseDTO.ok();
    }

    // TODO
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<DeliveryPersonDTO>> register(@RequestParam DeliveryPersonRequestDTO dto){
        return ResponseDTO.ok();
    }

    // TODO
    @GetMapping("/order/list")
    public ResponseEntity<ResponseDTO<List<OrderDTO>>> getOrderList(){
        return ResponseDTO.ok();
    }

    // TODO
    @PostMapping("/order/confirm/{deliverId}")
    public ResponseEntity<ResponseDTO<Boolean>> confirm(@PathVariable("deliverId") Long deliverId){
        return ResponseDTO.ok();
    }

    // TODO
    @PostMapping("order/close/{deliverId}")
    public ResponseEntity<ResponseDTO<Boolean>> close(@PathVariable("deliverId") Long deliverId){
        return ResponseDTO.ok();
    }

    // TODO
    @GetMapping("order/my/{deliverId}")
    public ResponseEntity<ResponseDTO<OrderDTO>> getMyOrder(@PathVariable("deliverId") Long deliverId){
        return ResponseDTO.ok();
    }
}