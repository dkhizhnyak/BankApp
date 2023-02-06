package com.example.bankapp.controller;

import com.example.bankapp.dto.TransferDto;
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

    @PostMapping("/transaction/transfer")
    public String transfer(@RequestBody TransferDto dto) {
        transactionService.transferTransaction(dto.getFrom(), dto.getTo(), dto.getAmount());
        return String.format("Successfully transferred money from account with id %s to account with id %s with amount $%s", dto.getFrom(), dto.getTo(), dto.getAmount());
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

    @PostMapping("/deposit/{id}")
    public String deposit(@PathVariable Long id, @RequestBody BigDecimal amount) {
        transactionService.depositTransaction(id, amount);
        return "Account is successfully updated";
    }

    @PostMapping("/withdrawal/{id}")
    public String withdrawal(@RequestBody BigDecimal amount, @PathVariable Long id) {
        transactionService.withdrawalTransaction(id, amount);
        return "Account is successfully updated";
    }

    @GetMapping("/transactions/period")
    public List<Transaction> transactionsByPeriod(@RequestParam String from, @RequestParam String to) throws Exception {
        return transactionService.getAllTransactionsByPeriod(from, to);
    }
}
