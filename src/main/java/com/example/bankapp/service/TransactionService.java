package com.example.bankapp.service;

import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Transaction;
import com.example.bankapp.persistance.AccountRepository;
import com.example.bankapp.persistance.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        try {
            fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
            accountRepository.save(fromAccount);
            createTransaction(fromAccount, amount, "WRITE-OFF");
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }

        try {
            toAccount.setBalance(toAccount.getBalance().add(amount));
            accountRepository.save(toAccount);
            createTransaction(toAccount, amount, "REFILL");
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
    }

    public void depositTransaction(Long accountId, BigDecimal amount) {
        Account account = accountService.getAccount(accountId);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        createTransaction(account, amount, "DEPOSIT");
    }

    public void withdrawalTransaction(Long accountId, BigDecimal amount) {
        Account account = accountService.getAccount(accountId);
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
        createTransaction(account, amount, "WITHDRAWAL");
    }

    public Transaction getTransaction(Long id) {
        return transactionRepository.getReferenceById(id);
    }

    public List<Transaction> getTransactionsByAccountId(Long id) {
        return transactionRepository.findAllByAccountId(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getAllTransactionsByPeriod(String from, String to) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date fromDate = format.parse(from);
        Date toDate = format.parse(to);

        return transactionRepository.findTransactionsByDateBetween(fromDate, toDate);
    }
}
