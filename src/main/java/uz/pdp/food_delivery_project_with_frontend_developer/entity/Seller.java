package uz.pdp.food_delivery_project_with_frontend_developer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.base.BaseEntity;
import uz.pdp.food_delivery_project_with_frontend_developer.enums.SellerStatus;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Seller extends BaseEntity {

    @OneToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private SellerStatus status;

}