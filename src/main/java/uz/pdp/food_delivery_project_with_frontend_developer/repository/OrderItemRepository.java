package uz.pdp.food_delivery_project_with_frontend_developer.repository;

import org.apache.catalina.LifecycleState;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.OrderItem;
import uz.pdp.food_delivery_project_with_frontend_developer.repository.baserepository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends BaseRepository<OrderItem, Long> {

    Optional<OrderItem> findByIdAndOrderId(Long orderItemId, Long orderId);

    List<OrderItem> findAllByOrderId(Long orderId);

}