package uz.pdp.food_delivery_project_with_frontend_developer.mapper;

import org.mapstruct.Mapper;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.packagedto.PackageDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.Package;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.base.EntityMapper;

@Mapper(componentModel = "spring")
public interface PackageMapper extends EntityMapper<PackageDTO, Package> {}