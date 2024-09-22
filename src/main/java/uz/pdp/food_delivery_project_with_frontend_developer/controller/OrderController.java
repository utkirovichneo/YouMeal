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

    @GetMapping("/order-item/get/{customerId}/{orderItemId}")
    public ResponseEntity<ResponseDTO<OrderItemDTO>> get(@PathVariable("customerId") Long customerId,
                                                         @PathVariable("orderItemId") Long orderItemId){
        return ResponseDTO.ok(service.get(customerId, orderItemId));
    }

    @PostMapping("/order-item/add/{customerId}")
    public ResponseEntity<ResponseDTO<OrderItemDTO>> add(@RequestParam OrderItemRequestDTO dto,
                                                         @PathVariable("customerId") Long customerId){
        return ResponseDTO.ok(service.add(customerId, dto));
    }

    @PutMapping("/order-item/update/{customerId}/{orderItemId}")
    public ResponseEntity<ResponseDTO<OrderItemDTO>> update(@PathVariable("customerId") Long customerId,
                                                            @PathVariable("orderItemId") Long orderItemId,
                                                            @RequestBody OrderItemRequestDTO dto){
        return ResponseDTO.ok(service.update(customerId, orderItemId, dto));
    }

    @DeleteMapping("/order-item/delete/{customerId}/{orderItemId}")
    public ResponseEntity<ResponseDTO<Boolean>> delete(@PathVariable("customerId") Long customerId,
                                                            @PathVariable("orderItemId") Long orderItemId){
        return ResponseDTO.ok(service.delete(customerId, orderItemId));
    }

    @GetMapping("/get/{customerId}")
    public ResponseEntity<ResponseDTO<OrderDTO>> getOrder(@PathVariable("customerId") Long customerId){
        return ResponseDTO.ok(service.get(customerId));
    }

    @PutMapping("/confirm/{customerId}")
    public ResponseEntity<ResponseDTO<Boolean>> confirm(@PathVariable("customerId") Long customerId){
        return ResponseDTO.ok(service.confirm(customerId));
    }

    @PutMapping("/reject/{customerId}")
    public ResponseEntity<ResponseDTO<Boolean>> reject(@PathVariable("customerId") Long customerId){
        return ResponseDTO.ok(service.reject(customerId));
    }
}