package com.bank.demo.transaction;

public class TransactionResponse {

    private final TransactionStatus status;

    public TransactionResponse(TransactionStatus status) {
        this.status = status;
    }

    public TransactionStatus getStatus() {
        return status;
    }

}
