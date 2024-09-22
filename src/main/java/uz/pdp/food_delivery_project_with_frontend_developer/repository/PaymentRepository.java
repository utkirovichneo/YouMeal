package uz.pdp.food_delivery_project_with_frontend_developer.repository;

import uz.pdp.food_delivery_project_with_frontend_developer.entity.Payment;
import uz.pdp.food_delivery_project_with_frontend_developer.repository.baserepository.BaseRepository;

import java.util.Optional;

public interface PaymentRepository extends BaseRepository<Payment, Long> {

    Optional<Payment> findByOrderIdAndCustomerId(Long orderId, Long customerId);

}