package org.lombold.blueprints.hexagonal.config;

import org.lombold.blueprints.hexagonal.application.port.primary.TransferMoneyUseCase;
import org.lombold.blueprints.hexagonal.application.port.secondary.AccountRepositoryPort;
import org.lombold.blueprints.hexagonal.application.service.TransferMoneyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public TransferMoneyUseCase transferMoneyUseCase(final AccountRepositoryPort accountRepositoryPort) {
        return new TransferMoneyService(accountRepositoryPort);
    }
}
