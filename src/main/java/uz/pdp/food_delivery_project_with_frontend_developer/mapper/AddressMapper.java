package uz.pdp.food_delivery_project_with_frontend_developer.mapper;

import org.mapstruct.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.address.AddressDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.Address;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.base.EntityMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {
    @Override
    Address toEntity(AddressDTO dto);

    @Override
    @Mapping(target = "location.latitude", source = "location.latitude")
    @Mapping(target = "location.longitude", source = "location.longitude")
    @Mapping(target = "location.id", source = "location.id")
    AddressDTO toDto(Address entity);

    @Override
    List<Address> toEntity(List<AddressDTO> list);

    @Override
    List<AddressDTO> toDto(List<Address> list);
}