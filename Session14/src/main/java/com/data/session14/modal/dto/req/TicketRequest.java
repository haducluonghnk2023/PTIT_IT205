package com.data.session14.modal.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    private Long showtimeId;
    private String seatNumber;
    private BigDecimal price;
}
