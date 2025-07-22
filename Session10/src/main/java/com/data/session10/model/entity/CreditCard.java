package com.data.session10.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "credit_card")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCard {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "account_id", unique = true)
    private Account account;

    private BigDecimal spendingLimit;

    private BigDecimal amountSpent;

    private String status; // "active", "inactive"
}
