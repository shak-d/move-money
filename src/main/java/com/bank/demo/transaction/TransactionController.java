package com.bank.demo.transaction;

import java.math.BigDecimal;
import java.util.Arrays;

import com.bank.demo.account.Account;
import com.bank.demo.account.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/transaction")
public class TransactionController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping(path = "/new")
    public @ResponseBody TransactionResponse sendMoney(@RequestParam(name = "sender") int senderId,
            @RequestParam(name = "recipient") int recipientId, @RequestParam(name = "amount") BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            return new TransactionResponse(TransactionStatus.INVALID_AMOUNT);

        Account sender = null;
        Account recipient = null;
        Iterable<Account> accounts = accountRepository.findAllById(Arrays.asList(senderId, recipientId));
        for (Account account : accounts)
            if (account.getId() == senderId)
                sender = account;
            else if (account.getId() == recipientId)
                recipient = account;

        if (sender == null)
            return new TransactionResponse(TransactionStatus.SENDER_NOT_FOUND);
        else if (recipient == null)
            return new TransactionResponse(TransactionStatus.RECIPIENT_NOT_FOUND);
        else if (amount.compareTo(sender.getBalance()) == 1)
            return new TransactionResponse(TransactionStatus.LOW_BALANCE);

        sender.setBalance(sender.getBalance().subtract(amount));
        recipient.setBalance(recipient.getBalance().add(amount));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setSender(sender);
        transaction.setRecipient(recipient);

        accountRepository.saveAll(Arrays.asList(sender,recipient));
        transactionRepository.save(transaction);

        return new TransactionResponse(TransactionStatus.SUCCESSFUL);
    }

}
