package uz.pdp.food_delivery_project_with_frontend_developer.mapper;

import org.mapstruct.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.customer.CustomerDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.Customer;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.base.EntityMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {
    @Override
    Customer toEntity(CustomerDTO dto);

    @Override
    CustomerDTO toDto(Customer entity);

    @Override
    List<Customer> toEntity(List<CustomerDTO> list);

    @Override
    List<CustomerDTO> toDto(List<Customer> list);
}
