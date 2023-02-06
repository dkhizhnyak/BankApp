package com.example.bankapp.web;

import com.example.bankapp.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AccountWebController {

    private final AccountService accountService;

    public AccountWebController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts/{id}")
    public String accountsHTML(@PathVariable Long id, Model model) {
        model.addAttribute("accounts", accountService.getAllAccountsByClientId(id));
        return "accounts";
    }

    @GetMapping("/accounts/{id}/new")
    public String newAccountHTML(@PathVariable Long id, Model model) {
        model.addAttribute("accountId", id);
        return "newAccount";
    }
}
