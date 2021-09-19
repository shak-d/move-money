package com.bank.demo.transaction;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer>{
    
    List<Transaction> findByAccountId(int accountId);
}
