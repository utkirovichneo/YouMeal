package uz.pdp.food_delivery_project_with_frontend_developer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.base.BaseEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Customer extends BaseEntity {

    @OneToOne
    private User user;

    @OneToOne
    private Address address;
}