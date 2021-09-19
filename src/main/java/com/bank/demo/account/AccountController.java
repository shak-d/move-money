package com.bank.demo.account;

import java.util.List;

import com.bank.demo.account.exceptions.AccountNotFoundException;
import com.bank.demo.transaction.Transaction;
import com.bank.demo.transaction.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/{id}")
    public @ResponseBody Statement getStatement(@PathVariable(value = "id") int accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
        List<Transaction> accountTransactions = transactionRepository.findByAccountId(accountId);

        Statement statement = new Statement();
        statement.setBalance(account.getBalance());
        statement.setTransactions(accountTransactions);

        return statement;
    }

}
