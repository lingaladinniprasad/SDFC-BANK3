package com.example.demo.controller;

//package com.sdfc.bank.controller;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.service.AccountService;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/newAccount")
    public String newAccountForm(Model model) {
        model.addAttribute("account", new Account());
        return "newAccount";
    }

    @PostMapping("/newAccount")
    public String createAccount(@ModelAttribute Account account, Model model) {
        accountService.createAccount(account);
        model.addAttribute("message", "Account created successfully!");
        return "home";
    }

    @GetMapping("/balance")
    public String balanceForm() {
        return "balance";
    }

    @PostMapping("/balance")
    public String getBalance(@RequestParam Long accountNumber, Model model) {
        double balance = accountService.getBalance(accountNumber);
        model.addAttribute("balance", balance);
        return "balance";
    }

    @GetMapping("/deposit")
    public String depositForm() {
        return "deposit";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam Long accountNumber, @RequestParam double amount, Model model) {
        accountService.deposit(accountNumber, amount);
        model.addAttribute("accountNumber", accountNumber);
        model.addAttribute("message", "Amount deposited successfully.");
        return "home";
    }

    @GetMapping("/withdraw")
    public String withdrawForm() {
        return "withdraw";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam Long accountNumber, @RequestParam double amount, Model model) {
        try {
            accountService.withdraw(accountNumber, amount);
            model.addAttribute("message", "Withdrawal successful!");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "home";
    }

    @GetMapping("/transfer")
    public String transferForm() {
        return "transfer";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam Long fromAccount, @RequestParam Long toAccount, @RequestParam double amount, Model model) {
        try {
            accountService.transfer(fromAccount, toAccount, amount);
            model.addAttribute("message", "Transfer successful!");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "home";
    }

    @GetMapping("/closeAccount")
    public String closeAccountForm() {
        return "closeAccount";
    }

    @PostMapping("/closeAccount")
    public String closeAccount(@RequestParam Long accountNumber, Model model) {
        accountService.deleteAccount(accountNumber);
        model.addAttribute("message", "Account closed successfully!");
        return "home";
    }
}
