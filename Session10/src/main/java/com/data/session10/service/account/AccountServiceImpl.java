package com.data.session10.service.account;

import com.data.session10.exception.NotFoundException;
import com.data.session10.model.entity.Account;
import com.data.session10.repository.AccountRepository;
import com.data.session10.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Account create(Account account) {
        account.setCitizenIdentificationCard(UUID.randomUUID().toString());
        return accountRepository.save(account);
    }

    @Override
    public Account update(UUID id, Account updatedAccount) {
        Account acExist =  accountRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Account not found with id: " + id));
        log.info("Before update: {}", acExist);
        log.info("After update: {}", updatedAccount);


        acExist.setFullName(updatedAccount.getFullName());
        acExist.setPhone(updatedAccount.getPhone());
        acExist.setEmail(updatedAccount.getEmail());
        acExist.setCitizenIdentificationCard(updatedAccount.getCitizenIdentificationCard());
        acExist.setMoney(updatedAccount.getMoney());
        acExist.setStatus(updatedAccount.getStatus());

        return accountRepository.save(acExist);
    }

    @Override
    public void delete(UUID id) {
        Account acc = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account not found"));
        acc.setStatus(com.data.session10.model.entity.StatusEnum.INACTIVE);
        accountRepository.save(acc);
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getByCitizenIdentificationCard(String citizenIdentificationCard) {
        return accountRepository.findByCitizenIdentificationCard(citizenIdentificationCard).
                orElseThrow(() -> new NotFoundException("Account not found with id: " + citizenIdentificationCard));
    }
}
