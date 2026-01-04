package org.lombold.blueprints.cleanarchitecture.frameworks.web.spring;

import org.lombold.blueprints.cleanarchitecture.adapter.controllers.TransferMoneyController;
import org.lombold.blueprints.cleanarchitecture.adapter.gateways.AccountRepositoryAdapter;
import org.lombold.blueprints.cleanarchitecture.application.TransferMoneyService;
import org.lombold.blueprints.cleanarchitecture.application.output.AccountRepository;
import org.lombold.blueprints.cleanarchitecture.frameworks.persistency.jpa.JpaAccountRepositoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CleanArchitectureConfiguration {

    @Bean
    public TransferMoneyService transferMoneyService(final AccountRepository accountRepository) {
        return new TransferMoneyService(accountRepository);
    }

    @Bean
    public TransferMoneyController transferMoneyController(final TransferMoneyService transferMoneyService) {
        return new TransferMoneyController(transferMoneyService);
    }

    @Bean
    public AccountRepository accountRepository(final JpaAccountRepositoryGateway jpaAccountRepositoryGateway) {
        return new AccountRepositoryAdapter(jpaAccountRepositoryGateway);
    }

}

