package com.data.session08.service;


import com.data.session08.model.req.AccountRequestDTO;

public interface AccountSerivce {
    AccountRequestDTO getAccountById(Long id);
}
