package uz.pdp.food_delivery_project_with_frontend_developer.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.User;
import uz.pdp.food_delivery_project_with_frontend_developer.repository.baserepository.BaseRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findUserByPhoneNumber(String username);
}