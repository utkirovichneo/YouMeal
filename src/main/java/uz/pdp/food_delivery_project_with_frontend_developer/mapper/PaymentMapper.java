package uz.pdp.food_delivery_project_with_frontend_developer.mapper;

import org.mapstruct.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.payment.PaymentDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.Payment;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.base.EntityMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper extends EntityMapper<PaymentDTO, Payment> {
    @Override
    Payment toEntity(PaymentDTO dto);

    @Override
    PaymentDTO toDto(Payment entity);

    @Override
    List<Payment> toEntity(List<PaymentDTO> list);

    @Override
    List<PaymentDTO> toDto(List<Payment> list);
}