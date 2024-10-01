package uz.pdp.food_delivery_project_with_frontend_developer.mapper;

import org.mapstruct.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.deliveryperson.DeliveryPersonDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.DeliveryPerson;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.base.EntityMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveryPersonMapper extends EntityMapper<DeliveryPersonDTO, DeliveryPerson> {
    @Override
    DeliveryPerson toEntity(DeliveryPersonDTO dto);

    @Override
    @Mapping(target = "vehicleType", source = "type")
    DeliveryPersonDTO toDto(DeliveryPerson entity);

    @Override
    List<DeliveryPerson> toEntity(List<DeliveryPersonDTO> list);

    @Override
    List<DeliveryPersonDTO> toDto(List<DeliveryPerson> list);
}
