package uz.pdp.food_delivery_project_with_frontend_developer.mapper;

import org.mapstruct.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.order.OrderDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.Order;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.base.EntityMapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
    CustomerMapper.class,
        ProductMapper.class
})
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {
    @Override
    Order toEntity(OrderDTO dto);

    @Override
    @Mapping(target = "customerId", source = "customer.id")
    OrderDTO toDto(Order entity);

    @Override
    List<Order> toEntity(List<OrderDTO> list);

    @Override
    List<OrderDTO> toDto(List<Order> list);
}
