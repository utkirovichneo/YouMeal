package uz.pdp.food_delivery_project_with_frontend_developer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.customer.CustomerDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.customer.CustomerRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.payment.PaymentRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.service.CustomerService;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthResponseDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.ResponseDTO;

@RestController
@RequestMapping("/api/customer/")
public record CustomerController(CustomerService service) {

    // TODO
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> login(@RequestParam AuthRequestDTO dto){
        return ResponseDTO.ok();
    }

    // TODO
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<CustomerDTO>> register(@RequestBody CustomerRequestDTO dto){
        return ResponseDTO.ok();
    }

    // TODO
    @GetMapping("/order/{customerId}")
    public ResponseEntity<ResponseDTO<CustomerDTO>> getOrder(@PathVariable("customerId") Long customerId){
        return ResponseDTO.ok();
    }

    // TODO
    @PutMapping("/order/payment/{customerId}")
    public ResponseEntity<ResponseDTO<Boolean>> payment(@PathVariable("customerId") Long customerId,
                                                        @RequestBody PaymentRequestDTO dto){
        return ResponseDTO.ok();
    }
}