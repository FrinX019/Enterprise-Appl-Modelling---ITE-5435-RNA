package com.fp.midterm.model;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
@TypeAlias("savings")
public class Savings extends Account {
    private double interestRate;

    public Savings() {
        super();
    }

    public Savings(String id, String accountHolder, double balance, double interestRate) {
        super(id, accountHolder, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void depositMonthlyInterest() {
        double interest = getBalance() * (interestRate / 100);
        deposit(interest);
    }
}
