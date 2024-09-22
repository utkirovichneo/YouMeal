package uz.pdp.food_delivery_project_with_frontend_developer.dto.customer;

import lombok.*;

@Getter
@Setter
public class CustomerRequestDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
}