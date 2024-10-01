package uz.pdp.food_delivery_project_with_frontend_developer.service.imp;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.order.OrderDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.orderitem.OrderItemDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.dto.orderitem.OrderItemRequestDTO;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.Order;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.OrderItem;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.Payment;
import uz.pdp.food_delivery_project_with_frontend_developer.enums.OrderStatus;
import uz.pdp.food_delivery_project_with_frontend_developer.enums.PaymentStatus;
import uz.pdp.food_delivery_project_with_frontend_developer.enums.PaymentType;
import uz.pdp.food_delivery_project_with_frontend_developer.exception.NotFoundException;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.OrderItemMapper;
import uz.pdp.food_delivery_project_with_frontend_developer.mapper.OrderMapper;
import uz.pdp.food_delivery_project_with_frontend_developer.repository.*;
import uz.pdp.food_delivery_project_with_frontend_developer.service.OrderService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderMapper orderMapper;
    private final PaymentRepository paymentRepository;

    @Override
    public OrderItemDTO add(Long customerId, OrderItemRequestDTO dto) {

            var order = orderRepository.findByCustomerIdAndStatus(customerId, OrderStatus.PENDING);
        if(order == null){
            order = orderRepository.save(Order.builder()
                    .customer(customerRepository.findById(customerId)
                            .orElseThrow(() -> new NotFoundException("Customer not found")))
                    .status(OrderStatus.PENDING)
                    .totalAmount(0)
                    .quantityOrderItems(0)
                    .build());
        }

        var orderItem = orderItemRepository.save(OrderItem.builder()
                .order(order)
                .product(productRepository.findById(dto.getProductId()).orElseThrow(() -> new NotFoundException("Product not found")))
                .quantity(dto.getQuantity())
                .build());

        order.setQuantityOrderItems(order.getQuantityOrderItems() + orderItem.getQuantity());
        order.setTotalAmount(order.getTotalAmount() + orderItem.getQuantity() * orderItem.getProduct().getPrice());
        orderRepository.save(order);

        return orderItemMapper.toDto(orderItem);

    }

    @Override
    @Transactional
    public OrderItemDTO update(Long customerId, Long orderItemId, OrderItemRequestDTO dto) {

        var orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new NotFoundException("OrderItem not found"));
            var order = orderItem.getOrder();

            order.setQuantityOrderItems(order.getQuantityOrderItems() - orderItem.getQuantity());
       order.setTotalAmount(order.getTotalAmount() - orderItem.getQuantity() * orderItem.getProduct().getPrice());

       orderItem.setQuantity(dto.getQuantity());

       orderItem = orderItemRepository.save(orderItem);

        order.setQuantityOrderItems(order.getQuantityOrderItems() + orderItem.getQuantity());
        order.setTotalAmount(order.getTotalAmount() + orderItem.getQuantity() * orderItem.getProduct().getPrice());

        orderRepository.save(order);

        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public OrderItemDTO get(Long customerId, Long orderItemId) {
        var order = orderRepository.findByCustomerIdAndStatus(customerId, OrderStatus.PENDING);
        if(order == null){
            throw new NotFoundException("Order not found");
        }
        var orderItem = orderItemRepository.findByIdAndOrderId(orderItemId, order.getId())
                .orElseThrow(() -> new NotFoundException("Order item not found"));
        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public Boolean delete(Long customerId, Long orderItemId) {

        var order = orderRepository.findByCustomerIdAndStatus(customerId, OrderStatus.PENDING);
            if(order == null){
                throw new NotFoundException("Order not found");
            }
            var orderItem = orderItemRepository.findByIdAndOrderId(orderItemId, order.getId())
                    .orElseThrow(()->new NotFoundException("Order item not found"));

            order.setTotalAmount(order.getTotalAmount() - orderItem.getQuantity() * orderItem.getProduct().getPrice());
                order.setQuantityOrderItems(order.getQuantityOrderItems() - orderItem.getQuantity());
                    orderRepository.save(order);

        orderItemRepository.deleteById(orderItem.getId());

        return Boolean.TRUE;
    }

    @Override
    public OrderDTO get(Long customerId) {
        var order = orderRepository.findByCustomerIdAndStatus(customerId, OrderStatus.PENDING);
            if(order == null){
                throw new NotFoundException("Order not found");
            }
        var orderDTO = orderMapper.toDto(order);
            List<OrderItemDTO> dto = orderItemMapper.toDto(orderItemRepository.findAllByOrderId(order.getId()));
                 orderDTO.setOrderItems(new HashSet<>(dto));
        return orderDTO;
    }

    @Override
    public Boolean confirm(Long customerId) {

        var order = orderRepository.findByCustomerIdAndStatus(customerId, OrderStatus.PENDING);

        if(order == null){
            throw new NotFoundException("Order not found");
        }

            order.setStatus(OrderStatus.CONFIRMED);

        orderRepository.save(order);

        paymentRepository.save(
                Payment.builder()
                        .order(order)
                        .type(PaymentType.CASH)
                        .status(PaymentStatus.PROCESSING)
                        .amount(order.getTotalAmount())
                        .customer(customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Customer not found")))
                        .description("Yetkazilib borilganda to'lov amalga oshiriladi")
                        .build()
        );

        return Boolean.TRUE;
    }

    @Override
    public Boolean reject(Long customerId) {

        var order = orderRepository.findByCustomerIdAndStatus(customerId, OrderStatus.PENDING);

        if(order == null){
            throw new NotFoundException("Order not found");
        }

        order.setStatus(OrderStatus.CANCELLED);

        orderRepository.save(order);

        return Boolean.TRUE;

    }
}