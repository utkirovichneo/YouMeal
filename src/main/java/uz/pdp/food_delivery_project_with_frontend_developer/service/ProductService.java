package uz.pdp.food_delivery_project_with_frontend_developer.service;

import org.springframework.data.domain.Page;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.product.ProductDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.product.ProductRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.PageableRequest;

public interface ProductService {

    ProductDTO get(Long productId, Long restaurantId);

    ProductDTO create(ProductRequestDTO dto, Long restaurantId);

    ProductDTO update(ProductRequestDTO dto, Long restaurantId, Long productId);

    Boolean delete(Long productId, Long restaurantId);

    Page<ProductDTO> getAll(PageableRequest pageable);

    Page<ProductDTO> getAllByRestaurant(Long restaurantId, PageableRequest pageable);

}
