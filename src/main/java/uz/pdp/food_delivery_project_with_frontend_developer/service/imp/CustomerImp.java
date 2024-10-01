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
import uz.pdp.food_delivery_project_with_frontend_developer.dto.customer.CustomerDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.customer.CustomerRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.order.OrderDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.*;
import uz.pdp.food_delivery_project_with_frontend_developer.enums.PaymentStatus;
import uz.pdp.food_delivery_project_with_frontend_developer.enums.PaymentType;
import uz.pdp.food_delivery_project_with_frontend_developer.exception.NotFoundException;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.CustomerMapper;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.OrderItemMapperImpl;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.OrderMapper;
import uz.pdp.food_delivery_project_with_frontend_developer.repository.*;
import uz.pdp.food_delivery_project_with_frontend_developer.service.CustomerService;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.AuthResponseDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.util.PageableRequest;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerImp implements CustomerService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LocationRepository locationRepository;
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final PaymentRepository paymentRepository;
    private final OrderItemMapperImpl orderItemMapper;
    private final OrderItemRepository orderItemRepository;

    @Override
    public CustomerDTO register(CustomerRequestDTO dto) {

        var role = roleRepository
                .findByNameEqualsIgnoreCase("ROLE_CUSTOMER")
                .orElseThrow(() -> new NotFoundException("Customer role not found"));

        var user = userRepository.save(
                User.builder()
                        .phoneNumber(dto.getUser().getPhoneNumber())
                        .password(passwordEncoder.encode(dto.getUser().getPassword()))
                        .roles(Set.of(role))
                        .build());

        var location = locationRepository.save(
                Location.builder()
                        .latitude(dto.getAddress().getLocation().getLatitude())
                        .longitude(dto.getAddress().getLocation().getLongitude())
                        .build()
        );

        var address = addressRepository.save(
                Address.builder()
                        .street(dto.getAddress().getStreet())
                        .apartment(dto.getAddress().getApartment())
                        .location(location)
                        .build()
        );

        var customer = customerRepository.save(
                Customer.builder()
                        .user(user)
                        .address(address)
                        .build()
        );
        return customerMapper.toDto(customer);
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
    public Page<OrderDTO> getOrders(PageableRequest pageable, Long customerId) {
        var orders = orderRepository.findAllByCustomerId(customerId);
            var orderDTOs = orderMapper.toDto(orders);
            orderDTOs.forEach(orderDTO ->
                orderDTO
                        .setOrderItems(new HashSet<>(
                                orderItemMapper.toDto(orderItemRepository.findAllByOrderId(orderDTO.getId())))));

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
    public Boolean payment(Long customerId, Long orderId) {

        var payment = paymentRepository
                .findByOrderIdAndCustomerId(orderId, customerId)
                .orElseThrow(()-> new NotFoundException("Payment not found"));

        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setType(PaymentType.CASH);

        paymentRepository.save(payment);

        return Boolean.TRUE;
    }
}