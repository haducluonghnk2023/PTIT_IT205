package com.data.session08.service.account;

import com.data.session08.model.entity.Account;
import com.data.session08.model.req.AccountRequestDTO;
import com.data.session08.repo.AccountRepository;
import com.data.session08.service.AccountSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountSerivceImpl implements AccountSerivce {
    @Autowired
    private AccountRepository  accountRepository;


    @Override
    public AccountRequestDTO getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
    }


    private AccountRequestDTO mapToDTO(Account account) {
        AccountRequestDTO dto = new AccountRequestDTO();
        dto.setUsername(account.getUsername());
        dto.setEmail(account.getEmail());
        dto.setPassword(account.getPassword());
        dto.setFullname(account.getFullname());
        dto.setPhone(account.getPhone());
        dto.setAddress(account.getAddress());
        return dto;
    }

}
