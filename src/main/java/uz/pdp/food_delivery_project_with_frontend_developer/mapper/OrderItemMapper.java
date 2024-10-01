package uz.pdp.food_delivery_project_with_frontend_developer.mapper;

import org.mapstruct.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.orderitem.OrderItemDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.OrderItem;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.base.EntityMapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        ProductMapper.class
})
public interface OrderItemMapper extends EntityMapper<OrderItemDTO, OrderItem> {
    @Override
    OrderItem toEntity(OrderItemDTO dto);

    @Override
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "productId", source = "product.id")
    OrderItemDTO toDto(OrderItem entity);

    @Override
    List<OrderItem> toEntity(List<OrderItemDTO> list);

    @Override
    List<OrderItemDTO> toDto(List<OrderItem> list);
}
