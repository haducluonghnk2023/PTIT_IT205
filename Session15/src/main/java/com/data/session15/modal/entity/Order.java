package com.data.session15.modal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "orders")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(length = 50, nullable = false)
    private String fullName;

    @Column(length = 200)
    private String address;

    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 20)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private OrderStatus status;
}
