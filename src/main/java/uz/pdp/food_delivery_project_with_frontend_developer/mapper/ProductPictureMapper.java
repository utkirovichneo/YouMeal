package uz.pdp.food_delivery_project_with_frontend_developer.mapper;

import org.mapstruct.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.productpicture.ProductPictureDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.ProductPicture;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.base.EntityMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductPictureMapper extends EntityMapper<ProductPictureDTO, ProductPicture> {}
