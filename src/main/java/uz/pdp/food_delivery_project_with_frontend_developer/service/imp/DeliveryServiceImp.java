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
import uz.pdp.food_delivery_project_with_frontend_developer.dto.deliveryperson.DeliveryPersonDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.deliveryperson.DeliveryPersonRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.order.OrderDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.packagedto.PackageDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.DeliveryPerson;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.Order;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.Package;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.User;
import uz.pdp.food_delivery_project_with_frontend_developer.enums.OrderStatus;
import uz.pdp.food_delivery_project_with_frontend_developer.enums.PackageStatus;
import uz.pdp.food_delivery_project_with_frontend_developer.exception.NotFoundException;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.DeliveryPersonMapper;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.OrderMapper;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.PackageMapper;
import uz.pdp.food_delivery_project_with_frontend_developer.repository.*;
import uz.pdp.food_delivery_project_with_frontend_developer.service.DeliveryService;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthResponseDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.PageableRequest;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImp implements DeliveryService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DeliveryPersonRepository deliveryPersonRepository;
    private final DeliveryPersonMapper deliveryPersonMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final PackageRepository packageRepository;
    private final PackageMapper packageMapper;

    @Override
    public DeliveryPersonDTO register(DeliveryPersonRequestDTO dto) {
        var role = roleRepository
                .findByNameEqualsIgnoreCase("ROLE_DELIVER")
                    .orElseThrow(()-> new NotFoundException("Role not found"));

        var user = userRepository.save(
                User.builder()
                        .phoneNumber(dto.getUser().getPhoneNumber())
                        .password(passwordEncoder.encode(dto.getUser().getPassword()))
                        .roles(Set.of(role))
                        .build()
        );

        var courier = deliveryPersonRepository.save(
                DeliveryPerson.builder()
                        .user(user)
                        .type(dto.getVehicleType())
                        .build()
        );

        return deliveryPersonMapper.toDto(courier);
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
    public Page<OrderDTO> orderList(PageableRequest pageable) {
        var orders = orderRepository.findAllByStatus(OrderStatus.CONFIRMED);
        var orderDTOs = orderMapper.toDto(orders);
                return new PageImpl<>(
                        orderDTOs,
                        PageRequest.of(
                                pageable.getPage(),
                                pageable.getPerPage()
                        ),
                        orders.size()
                );
    }

    @Override
    public Boolean confirm(Long orderId, Long deliverId) {

        var aPackage = Package.builder()
                .person(deliveryPersonRepository.findById(deliverId).orElseThrow(()-> new NotFoundException("Delivery person not found")))
                .order(orderRepository.findById(orderId).orElseThrow(()-> new NotFoundException("Order not found")))
                .status(PackageStatus.TRANSIT)
                .build();

        packageRepository.save(aPackage);

        return Boolean.TRUE;
    }

    @Override
    public Boolean close(Long orderId, Long deliverId) {
        var aPackage = packageRepository.findByOrderIdAndPersonId(orderId, deliverId).orElseThrow(()-> new NotFoundException("Order not found"));
        aPackage.setStatus(PackageStatus.DELIVERED);
        packageRepository.save(aPackage);
        return Boolean.TRUE;
    }

    @Override
    public Page<PackageDTO> myPackage(Long deliverId, PageableRequest pageable) {
        var packages = packageRepository.findAllByPersonId(deliverId);
            var packageDTOs = packageMapper.toDto(packages);
                return new PageImpl<>(
                        packageDTOs,
                        PageRequest.of(
                                pageable.getPage(),
                                pageable.getPerPage()
                        ),
                        packages.size()
                );
    }
}
