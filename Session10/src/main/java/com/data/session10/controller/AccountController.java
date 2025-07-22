package com.data.session10.controller;

import com.data.session10.model.entity.Account;
import com.data.session10.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    @Autowired
    private AccountService  accountService;

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account) {
        return ResponseEntity.status(201).body(accountService.create(account));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable UUID id, @RequestBody Account account) {
        return ResponseEntity.ok(accountService.update(id, account));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping("/cccd/{cccd}")
    public ResponseEntity<Account> getByCitizenIdentificationCard(@PathVariable("cccd") String cccd) {
        return ResponseEntity.ok(accountService.getByCitizenIdentificationCard(cccd));
    }

}
