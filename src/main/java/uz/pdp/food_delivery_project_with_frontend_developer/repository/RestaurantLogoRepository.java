package uz.pdp.food_delivery_project_with_frontend_developer.repository;

import uz.pdp.food_delivery_project_with_frontend_developer.entity.RestaurantLogo;
import uz.pdp.food_delivery_project_with_frontend_developer.repository.baserepository.BaseRepository;

import java.util.Optional;

public interface RestaurantLogoRepository extends BaseRepository<RestaurantLogo, Long> {

    Optional<RestaurantLogo> findByRestaurantId(Long restaurantId);

}