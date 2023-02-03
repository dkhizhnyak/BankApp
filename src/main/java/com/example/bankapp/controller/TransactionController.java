package com.example.bankapp.controller;

import com.example.bankapp.entity.Transaction;
import com.example.bankapp.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PutMapping("/transaction/transfer")
    public String transfer(@RequestParam Long from, Long to, BigDecimal amount) {
        transactionService.transferTransaction(from, to, amount);
        return String.format("Successfully transferred money from account with id %s to account with id %s with amount $%s", from, to, amount);
    }

    @GetMapping("/transaction/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransaction(id);
    }

    @GetMapping("/transaction/transactions/{id}")
    @ResponseBody
    public List<Transaction> getAllTransfersByAccount(@PathVariable Long id) {
        return transactionService.getTransactionsByAccountId(id);
    }

    @PutMapping("/transaction/deposit/{id}")
    public String deposit(@PathVariable Long id, @RequestBody BigDecimal amount) {
        transactionService.depositTransaction(id, amount);
        return "Account is successfully updated";
    }

    @PutMapping("/transaction/withdrawal/{id}")
    public String withdrawal(@RequestBody BigDecimal amount, @PathVariable Long id) {
        transactionService.withdrawalTransaction(id, amount);
        return "Account is successfully updated";
    }
}
