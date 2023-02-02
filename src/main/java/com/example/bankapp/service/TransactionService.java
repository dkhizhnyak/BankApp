package com.example.bankapp.service;

import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Transaction;
import com.example.bankapp.persistance.AccountRepository;
import com.example.bankapp.persistance.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    private final AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    private void createTransaction(Account account, BigDecimal amount, String operation) {
        Transaction transaction = new Transaction(account, amount, operation);
        transactionRepository.save(transaction);
    }

    @Transactional
    public void transferTransaction(Long from, Long to, BigDecimal amount) {
        Account fromAccount = accountService.getAccount(from);
        Account toAccount = accountService.getAccount(to);

        fromAccount.setAmount(fromAccount.getAmount().subtract(amount));
        accountRepository.save(fromAccount);
        createTransaction(fromAccount, amount, "WRITE-OFF");

        toAccount.setAmount(toAccount.getAmount().add(amount));
        accountRepository.save(toAccount);
        createTransaction(toAccount, amount, "REFILL");
    }

    public Transaction getTransaction(Long id) {
        return transactionRepository.getReferenceById(id);
    }

    public List<Transaction> getTransactionsByAccountId(Long id) {
        return transactionRepository.findAllByAccountId(id);
    }
}
