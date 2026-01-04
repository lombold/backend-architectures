package org.lombold.blueprints.onion.config;

import org.lombold.blueprints.onion.core.application.AccountRepository;
import org.lombold.blueprints.onion.core.application.TransferMoneyService;
import org.lombold.blueprints.onion.core.application.TransferMoneyUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public TransferMoneyUseCase transferMoneyUseCase(final AccountRepository accountRepository) {
        return new TransferMoneyService(accountRepository);
    }
}
