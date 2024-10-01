package uz.pdp.food_delivery_project_with_frontend_developer.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.customer.CustomerDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.customer.CustomerRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.order.OrderDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.service.CustomerService;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthResponseDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.PageableRequest;
import uz.pdp.food_delivery_project_with_frontend_developer.util.ResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/customer/")
public record CustomerController(CustomerService service) {

    @PostMapping("/login")
    @Operation(summary = "Klient login qiladi")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> login(@RequestBody AuthRequestDTO dto){
        return ResponseDTO.ok(service.login(dto));
    }

    @PostMapping("/register")
    @Operation(summary = "Klient register qiladi")
    public ResponseEntity<ResponseDTO<CustomerDTO>> register(@RequestBody CustomerRequestDTO dto){
        return ResponseDTO.ok(service.register(dto));
    }

    @PostMapping("/order/{customerId}")
    @Operation(summary = "Klient mavjud busketlarini ro'yxatini ko'radi")
    public ResponseEntity<ResponseDTO<List<OrderDTO>>> getOrders(@PathVariable("customerId") Long customerId,
                                                                 @RequestBody PageableRequest pageable){
        return ResponseDTO.page(service.getOrders(pageable, customerId));
    }

    @PutMapping("/order/payment/{customerId}/{orderId}")
    @Operation(summary = "To'lov qiladi")
    public ResponseEntity<ResponseDTO<Boolean>> payment(@PathVariable("customerId") Long customerId,
                                                        @PathVariable("orderId") Long orderId){
        return ResponseDTO.ok(service.payment(customerId, orderId));
    }
}