package com.fp.midterm.model;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
@TypeAlias("checking")
public class Checking extends Account {
    private double insufficientFundFee;

    public Checking() {
        super();
    }

    public Checking(String id, String accountHolder, double balance, double insufficientFundFee) {
        super(id, accountHolder, balance);
        this.insufficientFundFee = insufficientFundFee;
    }

    public double getInsufficientFundFee() {
        return insufficientFundFee;
    }

    public void setInsufficientFundFee(double insufficientFundFee) {
        this.insufficientFundFee = insufficientFundFee;
    }

    public void processingCheck(String check) {
        System.out.println("Processing check: " + check);
    }
}
