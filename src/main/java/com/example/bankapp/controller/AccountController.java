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

    @PostMapping("account/new")
    public String saveAccount(@RequestParam Long id, BigDecimal amount) {
        accountService.save(id, amount);
        return String.format("Account for user with id %s successfully saved", id);
    }

    @GetMapping("/accounts/{id}")
    public List<Account> getAllAccounts(@PathVariable Long id) {
        return accountService.getAllAccountsByClientId(id);
    }

}
