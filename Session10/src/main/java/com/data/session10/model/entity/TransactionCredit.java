package com.data.session10.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCredit {
    @Id
    private UUID id;

    @ManyToOne
    private Account accountReceiver;

    @ManyToOne
    private CreditCard creditCardSender;

    private String note;
    private BigDecimal money;

    private String status; // "thành công", "thất bại", "đang chờ xử lý"
    private LocalDateTime createdAt;
}
