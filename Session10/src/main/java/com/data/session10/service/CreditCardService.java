package com.data.session10.service;

import com.data.session10.model.entity.CreditCard;

import java.math.BigDecimal;
import java.util.UUID;

public interface CreditCardService {
    CreditCard create(CreditCard creditCard);
    CreditCard updateLimit(UUID id, BigDecimal newLimit);
    CreditCard updateStatus(UUID id, String newStatus);
}
