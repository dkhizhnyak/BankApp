package com.example.bankapp.controller;

import com.example.bankapp.entity.Account;
import com.example.bankapp.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("accounts/{id}/new")
    public Account saveAccount(@PathVariable Long id, @RequestBody BigDecimal amount) {
        return accountService.save(id, amount);
    }

    @GetMapping("/accounts/{id}")
    public List<Account> getAllAccounts(@PathVariable Long id) {
        return accountService.getAllAccountsByClientId(id);
    }

}
