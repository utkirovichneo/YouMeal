package uz.pdp.food_delivery_project_with_frontend_developer.mapper;

import org.mapstruct.*;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.user.UserResponseDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.User;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.base.EntityMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserResponseDTO, User> {

    @Override
    User toEntity(UserResponseDTO dto);

    @Override
    UserResponseDTO toDto(User entity);

    @Override
    List<User> toEntity(List<UserResponseDTO> list);

    @Override
    List<UserResponseDTO> toDto(List<User> list);
}
