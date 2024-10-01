package uz.pdp.food_delivery_project_with_frontend_developer.repository;

import uz.pdp.food_delivery_project_with_frontend_developer.entity.Product;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.ProductPicture;
import uz.pdp.food_delivery_project_with_frontend_developer.repository.baserepository.BaseRepository;

import java.util.Optional;

public interface ProductPictureRepository extends BaseRepository<ProductPicture, Long> {

    Optional<ProductPicture> findByProduct(Product product);

}