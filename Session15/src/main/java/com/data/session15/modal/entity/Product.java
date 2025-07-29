package com.data.session15.modal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Table(name = "products")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_name", length = 200, nullable = false)
    private String productName;

    @Column(name = "producer", length = 200, nullable = false)
    private String producer;
    private Date yearMaking;
    private Date expireDate;
    private Integer quantity;
    private BigDecimal price;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCart> productCarts;

}
