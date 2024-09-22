package uz.pdp.food_delivery_project_with_frontend_developer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@SpringBootApplication
public class FoodDeliveryProjectWithFrontendDeveloperApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodDeliveryProjectWithFrontendDeveloperApplication.class, args);
    }

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return new AuditorAware<Long>() {
            @Override
            public Optional<Long> getCurrentAuditor() {
                return Optional.empty();
            }
        };
    }

}
