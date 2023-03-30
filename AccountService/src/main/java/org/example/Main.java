package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.Account;
import org.example.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@Slf4j
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner init(final AccountRepository accountRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                Account account1 = new Account();
                account1.setAmount(BigDecimal.valueOf(10_000));
                var result1 = accountRepository.save(account1);
                log.info("account1 with id " + result1.getId() + " already created");

                Account account2 = new Account();
                account2.setAmount(BigDecimal.valueOf(20_000));
                var result2 = accountRepository.save(account2);
                log.info("account1 with id " + result2.getId() + " already created");
            }
        };
    }
}