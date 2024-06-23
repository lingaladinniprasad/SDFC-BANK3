package com.example.demo.service;

//public class AccountService {
//}
//package com.sdfc.bank.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> getAccount(Long accountNumber) {
        return accountRepository.findById(accountNumber);
    }

    public void deleteAccount(Long accountNumber) {
        accountRepository.deleteById(accountNumber);
    }

    public double getBalance(Long accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        return account.getBalance();
    }

    public void deposit(Long accountNumber, double amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void withdraw(Long accountNumber, double amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Insufficient balance");
        }
    }

    public void transfer(Long fromAccount, Long toAccount, double amount) {
        withdraw(fromAccount, amount);
        deposit(toAccount, amount);
    }
}
