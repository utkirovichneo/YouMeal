package uz.pdp.food_delivery_project_with_frontend_developer.mapper;

import org.mapstruct.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.seller.SellerDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.Seller;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.base.EntityMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SellerMapper extends EntityMapper<SellerDTO, Seller> {}
