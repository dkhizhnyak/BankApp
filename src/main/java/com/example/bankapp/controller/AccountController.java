package com.example.bankapp.controller;

import com.example.bankapp.entity.Account;
import com.example.bankapp.persistance.AccountRepository;
import com.example.bankapp.service.AccountService;
import com.example.bankapp.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class AccountController {

    private final AccountRepository accountRepository;

    private final ClientService clientService;

    private final AccountService accountService;

    public AccountController(AccountRepository accountRepository, ClientService clientService, AccountService accountService) {
        this.accountRepository = accountRepository;
        this.clientService = clientService;
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
