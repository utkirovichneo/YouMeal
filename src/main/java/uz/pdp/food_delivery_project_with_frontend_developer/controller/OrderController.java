package uz.pdp.food_delivery_project_with_frontend_developer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.order.OrderDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.orderitem.OrderItemDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.orderitem.OrderItemRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.service.OrderService;
import uz.pdp.food_delivery_project_with_frontend_developer.util.ResponseDTO;

@RestController
@RequestMapping("/api/order/")
public record OrderController(OrderService service) {

    // TODO
    @GetMapping("/order-item/get/{customerId}/{orderItemId}")
    public ResponseEntity<ResponseDTO<OrderItemDTO>> get(@PathVariable("customerId") Long customerId,
                                                         @PathVariable("orderItemId") Long orderItemId){
        return ResponseDTO.ok();
    }

    // TODO
    @PostMapping("/order-item/add/{customerId}")
    public ResponseEntity<ResponseDTO<OrderItemDTO>> add(@RequestParam OrderItemRequestDTO dto,
                                                         @PathVariable("customerId") Long customerId){
        return ResponseDTO.ok();
    }

    // TODO
    @PutMapping("/order-item/update/{customerId}/{orderItemId}")
    public ResponseEntity<ResponseDTO<OrderItemDTO>> update(@PathVariable("customerId") Long customerId,
                                                            @PathVariable("orderItemId") Long orderItemId){
        return ResponseDTO.ok();
    }

    // TODO
    @DeleteMapping("/order-item/delete/{customerId}/{orderItemId}")
    public ResponseEntity<ResponseDTO<OrderItemDTO>> delete(@PathVariable("customerId") Long customerId,
                                                            @PathVariable("orderItemId") Long orderItemId){
        return ResponseDTO.ok();
    }

    // TODO
    @GetMapping("/get/{customerId}")
    public ResponseEntity<ResponseDTO<OrderDTO>> getOrder(@PathVariable("customerId") Long customerId){
        return ResponseDTO.ok();
    }

    // TODO
    @PutMapping("/confirm/{customerId}")
    public ResponseEntity<ResponseDTO<Boolean>> confirm(@PathVariable("customerId") Long customerId){
        return ResponseDTO.ok();
    }

    // TODO
    @PutMapping("/reject/{customerId}")
    public ResponseEntity<ResponseDTO<Boolean>> reject(@PathVariable("customerId") Long customerId){
        return ResponseDTO.ok();
    }
}