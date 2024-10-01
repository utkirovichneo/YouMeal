package uz.pdp.food_delivery_project_with_frontend_developer.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.product.ProductDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.product.ProductRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.productpicture.ProductPictureDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.producttype.ProductTypeDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.producttype.ProductTypeRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.PageableRequest;

public interface ProductService {

    ProductDTO get(Long productId, Long restaurantId);

    ProductDTO create(ProductRequestDTO dto, Long restaurantId);

    ProductDTO update(ProductRequestDTO dto, Long restaurantId, Long productId);

    Boolean delete(Long productId, Long restaurantId);

    Page<ProductDTO> getAll(PageableRequest pageable);

    Page<ProductDTO> getAllByRestaurant(Long restaurantId, PageableRequest pageable);

    ProductPictureDTO upload(Long productId, Long restaurantId, MultipartFile logoPicture);

    String url(Long productId, Long restaurantId);

    ProductPictureDTO update(Long productId, Long restaurantId, MultipartFile logoPicture);

    ProductTypeDTO create(ProductTypeRequestDTO dto);

    Page<ProductTypeDTO> getAllType(PageableRequest pageable);

}
