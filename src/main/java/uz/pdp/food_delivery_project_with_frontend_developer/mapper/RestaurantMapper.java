package uz.pdp.food_delivery_project_with_frontend_developer.mapper;

import org.mapstruct.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurant.RestaurantDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.Restaurant;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.base.EntityMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper extends EntityMapper<RestaurantDTO, Restaurant> {}
