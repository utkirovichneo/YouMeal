package uz.pdp.food_delivery_project_with_frontend_developer.dto.product;

import lombok.*;

import java.util.Set;

@Getter
@Setter
public class ProductRequestDTO {
    private String name;
    private double price;
    private String description;
    private double weight;
    private double calories;
    private Long productTypeId;
    private Set<String> ingredients;
    private Long restaurantId;
}