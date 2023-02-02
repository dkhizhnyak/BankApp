package com.example.bankapp.service;

import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Client;
import com.example.bankapp.persistance.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    private final ClientService clientService;

    public AccountService(AccountRepository accountRepository, ClientService clientService) {
        this.accountRepository = accountRepository;
        this.clientService = clientService;
    }

    public void save(Long id, BigDecimal amount) {
        Client client = clientService.getClient(id);
        accountRepository.save(new Account(client, amount));
    }

    public Account getAccount(Long id) {
        return accountRepository.getReferenceById(id);
    }

    public List<Account> getAllAccountsByClientId(Long id) {
        return accountRepository.getAccountsByClientId(id);
    }
}
