package com.data.session10.service;

import com.data.session10.model.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    Account create(Account account);
    Account update(UUID id, Account updatedAccount);
    void delete(UUID id);
    List<Account> getAll();
    Account getByCitizenIdentificationCard(String citizenIdentificationCard);
}
