package uz.pdp.food_delivery_project_with_frontend_developer.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.product.ProductDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.product.ProductRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.Product;
import uz.pdp.food_delivery_project_with_frontend_developer.exception.NotFoundException;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.ProductMapper;
import uz.pdp.food_delivery_project_with_frontend_developer.repository.ProductRepository;
import uz.pdp.food_delivery_project_with_frontend_developer.repository.ProductTypeRepository;
import uz.pdp.food_delivery_project_with_frontend_developer.repository.RestaurantRepository;
import uz.pdp.food_delivery_project_with_frontend_developer.service.ProductService;
import uz.pdp.food_delivery_project_with_frontend_developer.util.PageableRequest;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductTypeRepository productTypeRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public ProductDTO get(Long productId, Long restaurantId) {
        var product = productRepository
                .findByIdAndRestaurantId(productId, restaurantId)
                .orElseThrow(()-> new NotFoundException("Product not found"));
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO create(ProductRequestDTO dto, Long restaurantId) {
            var a = new Product();
        var product = Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .ingredients(dto.getIngredients())
                .productType(productTypeRepository.findById(dto.getProductTypeId())
                        .orElseThrow(()-> new NotFoundException("Product type not found")))
                .weight(dto.getWeight())
                .calories(dto.getCalories())
                .restaurant(restaurantRepository.findById(restaurantId)
                        .orElseThrow(() -> new NotFoundException("Restaurant not found")))
                .build();

        return productMapper.toDto(productRepository.save(product));

    }

    @Override
    public ProductDTO update(ProductRequestDTO dto, Long restaurantId, Long productId) {
        var product = productRepository.findByIdAndRestaurantId(productId, restaurantId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setIngredients(dto.getIngredients());
        product.setWeight(dto.getWeight());
        product.setCalories(dto.getCalories());
        product.setProductType(productTypeRepository.findById(dto.getProductTypeId()).orElseThrow(()-> new NotFoundException("Product type not found")));

        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public Boolean delete(Long productId, Long restaurantId) {

        productRepository.deleteByIdAndRestaurantId(productId, restaurantId);

        return Boolean.TRUE;
    }

    @Override
    public Page<ProductDTO> getAll(PageableRequest pageable) {
            var products = productRepository.findAll();
                var productDTOs = productMapper.toDto(products);
                    return new PageImpl<>(productDTOs,
                            PageRequest.of(
                                    pageable.getPage(),
                                    pageable.getPerPage()),
                            products.size());
    }

    @Override
    public Page<ProductDTO> getAllByRestaurant(Long restaurantId, PageableRequest pageable) {
            var products = productRepository.findAllByRestaurantId(restaurantId);
                var productDTOs = productMapper.toDto(products);
                    return new PageImpl<>(productDTOs,
                            PageRequest.of(
                                    pageable.getPage(),
                                    pageable.getPerPage()),
                            products.size());

    }
}