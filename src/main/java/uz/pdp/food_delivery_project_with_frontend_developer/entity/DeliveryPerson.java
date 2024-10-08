package uz.pdp.food_delivery_project_with_frontend_developer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.base.BaseEntity;
import uz.pdp.food_delivery_project_with_frontend_developer.enums.VehicleType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class DeliveryPerson extends BaseEntity {

    @OneToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

}