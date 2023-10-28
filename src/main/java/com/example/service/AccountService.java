package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account){
        Optional<Account> accountByUsername = accountRepository.findAccountByUsername(account.getUsername());
        if(accountByUsername.isPresent()) {
            return null;
        }
        return accountRepository.save(account);
    }
}
