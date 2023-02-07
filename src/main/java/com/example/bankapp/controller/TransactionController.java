package com.example.bankapp.controller;

import com.example.bankapp.date.DateRange;
import com.example.bankapp.dto.TransferDto;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Transaction;
import com.example.bankapp.response.TransferStatus;
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

    @PostMapping("/transaction/transfer")
    public TransferStatus transfer(@RequestBody TransferDto dto) {
        return transactionService.transferTransaction(dto.getFrom(), dto.getTo(), dto.getAmount());
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransaction(id);
    }

    @GetMapping("/transactions/{id}")
    @ResponseBody
    public List<Transaction> getAllTransfersByAccount(@PathVariable Long id) {
        return transactionService.getTransactionsByAccountId(id);
    }

    @PostMapping("transaction/deposit/{id}")
    public Account deposit(@PathVariable Long id, @RequestBody BigDecimal amount) {
        return transactionService.depositTransaction(id, amount);
    }

    @PostMapping("transaction/withdrawal/{id}")
    public Account withdrawal(@RequestBody BigDecimal amount, @PathVariable Long id) {
        return transactionService.withdrawalTransaction(id, amount);
    }

    @GetMapping("/transactions/period")
    public List<Transaction> transactionsByPeriod(@RequestParam String from, @RequestParam String to) throws Exception {
        return transactionService.getAllTransactionsByPeriod(from, to);
    }

    @GetMapping("/api/transactions")
    public List<Transaction> allTransactions() {
        return transactionService.getAllTransactions();
    }
}
