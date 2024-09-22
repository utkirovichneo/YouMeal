package uz.pdp.food_delivery_project_with_frontend_developer.repository;

import uz.pdp.food_delivery_project_with_frontend_developer.entity.Restaurant;
import uz.pdp.food_delivery_project_with_frontend_developer.repository.baserepository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends BaseRepository<Restaurant, Long> {

    Optional<Restaurant> findByIdAndSellerId(Long id, Long sellerId);

    List<Restaurant> findBySellerId(Long sellerId);

}