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

    @GetMapping("/transaction/transactions/{id}")
    @ResponseBody
    public List<Transaction> getAllTransfersByAccount(@PathVariable Long id) {
        return transactionService.getTransactionsByAccountId(id);
    }
}
