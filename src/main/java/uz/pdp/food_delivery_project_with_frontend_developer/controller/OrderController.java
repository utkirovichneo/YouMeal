package uz.pdp.food_delivery_project_with_frontend_developer.controller;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Order-itemni oladi. Ya'ni Busketdagi bitta buyurtmani oladi")
    public ResponseEntity<ResponseDTO<OrderItemDTO>> get(@PathVariable("customerId") Long customerId,
                                                         @PathVariable("orderItemId") Long orderItemId){
        return ResponseDTO.ok(service.get(customerId, orderItemId));
    }

    @PostMapping("/order-item/add/{customerId}")
    @Operation(summary = "Busketga product add qiladi")
    public ResponseEntity<ResponseDTO<OrderItemDTO>> add(@RequestBody OrderItemRequestDTO dto,
                                                         @PathVariable("customerId") Long customerId){
        return ResponseDTO.ok(service.add(customerId, dto));
    }

    @PutMapping("/order-item/update/{customerId}/{orderItemId}")
    @Operation(summary = "Busketdagi mahsulotni update qiladi")
    public ResponseEntity<ResponseDTO<OrderItemDTO>> update(@PathVariable("customerId") Long customerId,
                                                            @PathVariable("orderItemId") Long orderItemId,
                                                            @RequestBody OrderItemRequestDTO dto){
        return ResponseDTO.ok(service.update(customerId, orderItemId, dto));
    }

    @DeleteMapping("/order-item/delete/{customerId}/{orderItemId}")
    @Operation(summary = "Busketdagi mahsulotni o'chiradi")
    public ResponseEntity<ResponseDTO<Boolean>> delete(@PathVariable("customerId") Long customerId,
                                                            @PathVariable("orderItemId") Long orderItemId){
        return ResponseDTO.ok(service.delete(customerId, orderItemId));
    }

    @GetMapping("/get/{customerId}")
    @Operation(summary = "Busketni get qiladi")
    public ResponseEntity<ResponseDTO<OrderDTO>> getOrder(@PathVariable("customerId") Long customerId){
        return ResponseDTO.ok(service.get(customerId));
    }

    @PutMapping("/confirm/{customerId}")
    @Operation(summary = "Klient busketni confirm qiladi ya'ni zakaz beradi")
    public ResponseEntity<ResponseDTO<Boolean>> confirm(@PathVariable("customerId") Long customerId){
        return ResponseDTO.ok(service.confirm(customerId));
    }

    @PutMapping("/reject/{customerId}")
    @Operation(summary = "Klient buyurtmani bekor qiladi. Ya'ni busketni o'chirib tashlaydi")
    public ResponseEntity<ResponseDTO<Boolean>> reject(@PathVariable("customerId") Long customerId){
        return ResponseDTO.ok(service.reject(customerId));
    }
}