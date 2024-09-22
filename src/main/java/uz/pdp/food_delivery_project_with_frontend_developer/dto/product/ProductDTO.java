package uz.pdp.food_delivery_project_with_frontend_developer.dto.product;

import lombok.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.producttype.ProductTypeDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurant.RestaurantDTO;

import java.util.Set;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private String description;
    private Set<String> ingredients;
    private ProductTypeDTO productType;
    private double weight;
    private double calories;
    private RestaurantDTO restaurant;
}