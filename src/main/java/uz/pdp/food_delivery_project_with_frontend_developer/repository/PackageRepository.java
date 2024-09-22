package uz.pdp.food_delivery_project_with_frontend_developer.repository;

import org.apache.catalina.LifecycleState;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.DeliveryPerson;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.Package;
import uz.pdp.food_delivery_project_with_frontend_developer.repository.baserepository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface PackageRepository extends BaseRepository<Package, Long> {

    Optional<Package> findByOrderIdAndPersonId(Long orderId, Long deliverId);

    List<Package> findAllByPersonId(Long deliverId);

}