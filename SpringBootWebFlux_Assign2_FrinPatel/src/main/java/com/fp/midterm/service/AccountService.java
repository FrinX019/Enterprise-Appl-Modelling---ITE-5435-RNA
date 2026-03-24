package com.fp.midterm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.fp.midterm.model.Account;
import com.fp.midterm.model.Checking;
import com.fp.midterm.model.Savings;
import com.fp.midterm.repository.AccountRepository;
import com.fp.midterm.repository.CheckingRepository;
import com.fp.midterm.repository.SavingsRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private SavingsRepository savingsRepository;

    public Mono<Account> addAccount(Account account) {
        return accountRepository.save(account);
    }

    public Flux<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Mono<Checking> addChecking(Checking checking) {
        return checkingRepository.save(checking);
    }

    public Flux<Checking> getAllCheckings() {
        return checkingRepository.findAll();
    }

    public Mono<Savings> addSavings(Savings savings) {
        return savingsRepository.save(savings);
    }

    public Flux<Savings> getAllSavings() {
        return savingsRepository.findAll();
    }

    public Mono<Account> withdraw(String id, double amount) {
        return accountRepository.findById(id).flatMap(account -> {
            account.withdrawal(amount);
            return accountRepository.save(account);
        });
    }
}
