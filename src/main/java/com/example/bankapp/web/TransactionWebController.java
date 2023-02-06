package com.example.bankapp.web;

import com.example.bankapp.date.DateRange;
import com.example.bankapp.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;


@Controller
public class TransactionWebController {

    private final TransactionService transactionService;

    public TransactionWebController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions/account/{id}")
    public String accountTransactionsHTML(@PathVariable Long id, Model model) {
        model.addAttribute("transactions", transactionService.getTransactionsByAccountId(id));
        return "accountTransactions";
    }

    @GetMapping("transactions/account/{id}/new")
    public String newTransactionHTML(@PathVariable Long id, Model model) {
        model.addAttribute("accountId", id);
        return "newTransaction";
    }

    @GetMapping("transactions/account/{id}/deposit")
    public String depositTransactionHTML(@PathVariable Long id, Model model) {
        model.addAttribute("accountId", id);
        return "depositTransaction";
    }

    @GetMapping("transactions/account/{id}/withdrawal")
    public String withdrawalTransactionHTML(@PathVariable Long id, Model model) {
        model.addAttribute("accountId", id);
        return "withdrawalTransaction";
    }

    @GetMapping("/transactions")
    public String allTransactionHTML(Model model) {
        DateRange dateRange = new DateRange();
        dateRange.setDateFrom(new Date());
        dateRange.setDateTo(new Date());
        model.addAttribute("dateRange", dateRange);
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "transactions";
    }

    @PostMapping("/transactions")
    public String transactionsByPeriodHTML(DateRange dateRange, Model model) {
        model.addAttribute("dateRange", dateRange);
        model.addAttribute("transactions", transactionService.getAllTransactionsByPeriod(dateRange));
        return "transactionsPeriod";
    }
}
