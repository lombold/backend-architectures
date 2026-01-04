package org.lombold.blueprints.cleanenough.config;

import org.lombold.blueprints.cleanenough.adapter.controllers.TransferMoneyController;
import org.lombold.blueprints.cleanenough.application.TransferMoneyService;
import org.lombold.blueprints.cleanenough.application.output.AccountRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CleanArchitectureConfiguration {

    @Bean
    public TransferMoneyService transferMoneyService(final AccountRepositoryPort accountRepositoryPort) {
        return new TransferMoneyService(accountRepositoryPort);
    }

    @Bean
    public TransferMoneyController transferMoneyController(final TransferMoneyService transferMoneyService) {
        return new TransferMoneyController(transferMoneyService);
    }
}

