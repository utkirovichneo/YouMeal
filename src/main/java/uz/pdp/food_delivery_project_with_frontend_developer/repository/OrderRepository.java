package uz.pdp.food_delivery_project_with_frontend_developer.repository;

import uz.pdp.food_delivery_project_with_frontend_developer.entity.Order;
import uz.pdp.food_delivery_project_with_frontend_developer.enums.OrderStatus;
import uz.pdp.food_delivery_project_with_frontend_developer.repository.baserepository.BaseRepository;

import java.util.List;

public interface OrderRepository extends BaseRepository<Order, Long> {

    Order findByCustomerIdAndStatus(Long customerId, OrderStatus status);

    List<Order> findAllByStatus(OrderStatus status);

    List<Order> findAllByCustomerId(Long customerId);
}