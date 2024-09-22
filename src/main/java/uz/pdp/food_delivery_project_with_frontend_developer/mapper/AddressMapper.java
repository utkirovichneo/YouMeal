package uz.pdp.food_delivery_project_with_frontend_developer.mapper;

import org.mapstruct.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.address.AddressDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.Address;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.base.EntityMapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {}