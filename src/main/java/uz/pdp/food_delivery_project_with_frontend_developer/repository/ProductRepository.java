package uz.pdp.food_delivery_project_with_frontend_developer.repository;

import uz.pdp.food_delivery_project_with_frontend_developer.entity.Product;
import uz.pdp.food_delivery_project_with_frontend_developer.repository.baserepository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends BaseRepository<Product, Long> {

    Optional<Product> findByIdAndRestaurantId(Long productId, Long restaurantId);

    void deleteByIdAndRestaurantId(Long productId, Long restaurantId);

    List<Product> findAllByRestaurantId(Long restaurantId);
}