package uz.pdp.food_delivery_project_with_frontend_developer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
public class RestaurantLogo extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String prefix;

    @Column(nullable = false)
    private String url;

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

}