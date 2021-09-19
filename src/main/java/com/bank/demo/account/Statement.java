package com.bank.demo.account;

import java.math.BigDecimal;
import java.util.List;

import com.bank.demo.transaction.Transaction;

public class Statement {

    BigDecimal balance;

    List<Transaction> transactions;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    
}
