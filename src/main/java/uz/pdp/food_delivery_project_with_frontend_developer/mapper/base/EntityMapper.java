package uz.pdp.food_delivery_project_with_frontend_developer.mapper.base;

import java.util.List;
    /**
    * D - DTO
    * E - Entity
    */
public interface EntityMapper<D, E> {
    E toEntity(D dto);
    D toDto(E entity);
    List<E> toEntity(List<D> list);
    List<D> toDto(List<E> list);
}