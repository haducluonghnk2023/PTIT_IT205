package com.data.session10.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode; // Import này
import org.hibernate.type.SqlTypes; // Import này

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "account") // Nên đặt tên bảng rõ ràng, nếu không Hibernate sẽ tự suy luận
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR) // <-- Thêm dòng này để lưu UUID dưới dạng chuỗi
    private UUID id;
    private String fullName;
    private String phone;
    private String citizenIdentificationCard;
    private String email;
    private BigDecimal money;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}