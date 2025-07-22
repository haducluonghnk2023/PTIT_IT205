package com.data.session10.service.credit_card;

import com.data.session10.model.entity.Account;
import com.data.session10.model.entity.CreditCard;
import com.data.session10.repository.AccountRepository;
import com.data.session10.repository.CreditCardRepository;
import com.data.session10.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final AccountRepository accountRepository;

    @Override
    public CreditCard create(CreditCard creditCard) {
        UUID accountId = creditCard.getAccount().getId();
        if (creditCardRepository.findByAccountId(accountId).isPresent()) {
            throw new RuntimeException("Account already has a credit card.");
        }

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        creditCard.setId(UUID.randomUUID());
        creditCard.setAccount(account);
        creditCard.setAmountSpent(BigDecimal.valueOf(0));
        return creditCardRepository.save(creditCard);
    }

    @Override
    public CreditCard updateLimit(UUID id, BigDecimal newLimit) {
        CreditCard card = creditCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit card not found"));
        card.setSpendingLimit(newLimit);
        return creditCardRepository.save(card);
    }

    @Override
    public CreditCard updateStatus(UUID id, String newStatus) {
        CreditCard card = creditCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit card not found"));
        card.setStatus(newStatus);
        return creditCardRepository.save(card);
    }
}
