package uz.pdp.food_delivery_project_with_frontend_developer.mapper;

import org.mapstruct.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.producttype.ProductTypeDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.Product;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.ProductType;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.base.EntityMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductTypeMapper extends EntityMapper<ProductTypeDTO, ProductType> {}
