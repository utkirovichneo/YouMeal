package uz.pdp.food_delivery_project_with_frontend_developer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import uz.pdp.food_delivery_project_with_frontend_developer.entity.base.BaseEntity;
import uz.pdp.food_delivery_project_with_frontend_developer.enums.OrderStatus;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems;

    @Column(nullable = false)
    private double totalAmount;

    private OrderStatus status;

    private Integer quantityOrderItems;
}
