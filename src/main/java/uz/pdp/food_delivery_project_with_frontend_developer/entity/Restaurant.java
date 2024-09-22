package uz.pdp.food_delivery_project_with_frontend_developer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
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
public class Restaurant extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Pattern(regexp = "^\\+998\\d{9}$", message = "Invalid phone number format for Uzbekistan")
    private String phone;

    @OneToOne
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;
}