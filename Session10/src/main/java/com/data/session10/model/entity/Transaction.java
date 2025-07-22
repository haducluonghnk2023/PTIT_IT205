package com.data.session10.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode; // Import này
import org.hibernate.type.SqlTypes; // Import này
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR) // <-- Thêm dòng này
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Account receiver;

    private Double money;

    private String note;

    private String status; // "thành công", "thất bại", "đang chờ xử lý"

    private LocalDateTime createdAt;
}