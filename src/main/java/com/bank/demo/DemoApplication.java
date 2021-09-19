package com.bank.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.bank.demo.account.Account;
import com.bank.demo.account.AccountRepository;
import com.bank.demo.transaction.TransactionRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication {

	private static final int TEST_ACCOUNTS = 2;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @Bean
    CommandLineRunner initTestDatabase(AccountRepository accountRepository, TransactionRepository transactionRepository) {

        return args -> {
            List<Account> accounts = new ArrayList<>(TEST_ACCOUNTS);
            for (int i = 0; i < TEST_ACCOUNTS; i++) {
                Account testAccount = new Account();
                testAccount.setId(i);
                testAccount.setBalance(new BigDecimal("1000"));
                accounts.add(testAccount);
            }
            accountRepository.saveAll(accounts);
            transactionRepository.deleteAll();
        };
    }
}
