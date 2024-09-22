package uz.pdp.food_delivery_project_with_frontend_developer.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.food_delivery_project_with_frontend_developer.config.service.JWTService;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurant.RestaurantDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurant.RestaurantNameUpdateDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.restaurant.RestaurantRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.seller.SellerDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.seller.SellerRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.*;
import uz.pdp.food_delivery_project_with_frontend_developer.enums.SellerStatus;
import uz.pdp.food_delivery_project_with_frontend_developer.exception.NotFoundException;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.RestaurantMapper;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.SellerMapper;
import uz.pdp.food_delivery_project_with_frontend_developer.repository.*;
import uz.pdp.food_delivery_project_with_frontend_developer.service.SellerService;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthResponseDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.PageableRequest;
import uz.pdp.food_delivery_project_with_frontend_developer.util.UtilService;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SellerServiceImp implements SellerService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final LocationRepository locationRepository;
    private final AddressRepository addressRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Override
    public SellerDTO register(SellerRequestDTO dto) {

        var role = roleRepository
                .findByNameEqualsIgnoreCase("ROLE_SELLER")
                .orElseThrow(() -> new NotFoundException("Seller role not found"));

        var user = userRepository.save(
                User.builder()
                        .phoneNumber(dto.getUser().getPhoneNumber())
                        .password(passwordEncoder.encode(dto.getUser().getPassword()))
                        .roles(Set.of(role))
                        .build());

        var seller = sellerRepository.save(
                Seller.builder()
                        .user(user)
                        .status(SellerStatus.ADMIN)
                        .build());

        return sellerMapper.toDto(seller);
    }

    @Override
    public AuthResponseDTO login(AuthRequestDTO dto) {
        var user = userRepository.findUserByPhoneNumber(
                dto.getPhoneNumber())
                .orElseThrow(()-> new NotFoundException("Bunday raqamli user mavjud emas"));

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Password noto'g'ri");
        }

        var authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getPhoneNumber(),
                dto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return AuthResponseDTO
                .builder()
                .accessToken(jwtService.accessToken(dto.getPhoneNumber()))
                .refreshToken(jwtService.refreshToken(dto.getPhoneNumber()))
                .build();
    }

    @Override
    public RestaurantDTO createRestaurant(Long sellerId, RestaurantRequestDTO dto) {

        var seller = sellerRepository
                .findById(sellerId)
                .orElseThrow(()->new NotFoundException("Seller not found"));


        var location = locationRepository.save(Location.builder()
                .latitude(dto.getAddress().getLocation().getLatitude())
                .latitude(dto.getAddress().getLocation().getLongitude())
                .build());

        var address = addressRepository.save(Address.builder()
                .street(dto.getAddress().getStreet())
                .apartment(dto.getAddress().getApartment())
                .location(location)
                .build()
        );

        var restaurant = restaurantRepository.save(Restaurant.builder()
                .seller(seller)
                .name(dto.getName())
                .phone(dto.getPhone())
                .address(address)
                .build());

        return restaurantMapper.toDto(restaurant);
    }

    @Override
    public RestaurantDTO getRestaurant(Long sellerId, Long restaurantId) {

        var restaurant = restaurantRepository.findByIdAndSellerId(restaurantId, sellerId)
                .orElseThrow(()->new NotFoundException("Restaurant not found"));

        return restaurantMapper.toDto(restaurant);
    }

    @Override
    public Page<RestaurantDTO> getSellerRestaurants(Long sellerId, PageableRequest pageable) {
        var sellerRestaurants = restaurantRepository.findBySellerId(sellerId);
            var restaurantDTOS = restaurantMapper.toDto(sellerRestaurants);
        return new PageImpl<>(restaurantDTOS,
                PageRequest.of(
                        pageable.getPage(),
                        pageable.getPerPage()
                ),
                sellerRestaurants.size());
    }

    @Override
    public Page<RestaurantDTO> getRestaurants(PageableRequest pageable) {
        var restaurants = restaurantRepository.findAll();
            var restaurantDTOS = restaurantMapper.toDto(restaurants);
        return new PageImpl<>(restaurantDTOS,
                PageRequest.of(
                        pageable.getPage(),
                        pageable.getPerPage()
                ),
                restaurantDTOS.size());
    }

    @Override
    public RestaurantDTO updateRestaurant(Long sellerId, Long restaurantId, RestaurantNameUpdateDTO dto) {
        var restaurant = restaurantRepository.findByIdAndSellerId(restaurantId, sellerId).orElseThrow(() -> new NotFoundException("Restaran topilmadi"));
            restaurant.setName(dto.getName());
                return restaurantMapper.toDto(restaurantRepository.save(restaurant));
    }
}