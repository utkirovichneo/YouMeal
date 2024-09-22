package uz.pdp.food_delivery_project_with_frontend_developer.dto.payment;

import lombok.*;
import uz.pdp.food_delivery_project_with_frontend_developer.enums.PaymentType;

@Getter
@Setter
public class PaymentRequestDTO {

    private PaymentType paymentType;

}