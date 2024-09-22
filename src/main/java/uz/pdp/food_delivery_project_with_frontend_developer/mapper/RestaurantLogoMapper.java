package uz.pdp.food_delivery_project_with_frontend_developer.mapper;

import org.mapstruct.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurantlogo.RestaurantLogoDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.RestaurantLogo;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.base.EntityMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantLogoMapper extends EntityMapper<RestaurantLogoDTO, RestaurantLogo> {}
