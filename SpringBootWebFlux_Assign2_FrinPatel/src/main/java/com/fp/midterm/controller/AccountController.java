package com.fp.midterm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.fp.midterm.model.Account;
import com.fp.midterm.model.Checking;
import com.fp.midterm.model.Savings;
import com.fp.midterm.service.AccountService;

@RestController
@RequestMapping("/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/accounts")
    public Mono<Account> addAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @GetMapping("/accounts")
    public Flux<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping("/checkings")
    public Mono<Checking> addChecking(@RequestBody Checking checking) {
        return accountService.addChecking(checking);
    }

    @GetMapping("/checkings")
    public Flux<Checking> getAllCheckings() {
        return accountService.getAllCheckings();
    }

    @PostMapping("/savings")
    public Mono<Savings> addSavings(@RequestBody Savings savings) {
        return accountService.addSavings(savings);
    }

    @GetMapping("/savings")
    public Flux<Savings> getAllSavings() {
        return accountService.getAllSavings();
    }

    @PutMapping("/accounts/{id}/withdraw")
    public Mono<Account> withdraw(@PathVariable String id, @RequestParam double amount) {
        return accountService.withdraw(id, amount);
    }
}
