package com.data.session08.controller;

import com.data.session08.model.req.AccountRequestDTO;
import com.data.session08.model.res.DataResponse;
import com.data.session08.service.AccountSerivce;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountSerivce  accountSerivce;
    @PostMapping
    public ResponseEntity<DataResponse<AccountRequestDTO>> postAccount(@Valid @RequestBody AccountRequestDTO account) {
        DataResponse<AccountRequestDTO> response = new DataResponse<>(account, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<AccountRequestDTO>> getAccountById(
            @PathVariable Long id) {
        AccountRequestDTO dto = accountSerivce.getAccountById(id);
        DataResponse<AccountRequestDTO> response = new DataResponse<>(dto, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }
}
