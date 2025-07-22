package com.data.session10.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode; // Import này
import org.hibernate.type.SqlTypes; // Import này
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR) // <-- Thêm dòng này
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private String content;

    private String status; // "đã đọc", "chưa đọc"

    private LocalDateTime createdAt;
}