package com.fp.midterm.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
public class Account {
    @Id
    private String id;
    private String accountHolder;
    private double balance;

    public Account() {
    }

    public Account(String id, String accountHolder, double balance) {
        this.id = id;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            updatebalance(this.balance + amount);
        }
    }

    public void withdrawal(double amount) {
        if (amount > 0 && this.balance >= amount) {
            updatebalance(this.balance - amount);
        }
    }

    private void updatebalance(double newBalance) {
        this.balance = newBalance;
    }
}
