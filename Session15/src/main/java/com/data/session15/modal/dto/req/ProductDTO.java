package com.data.session15.modal.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String productName;
    private String producer;
    private Date yearMaking;
    private Date expireDate;
    private Integer quantity;
    private BigDecimal price;
    private Long categoryId;
}
