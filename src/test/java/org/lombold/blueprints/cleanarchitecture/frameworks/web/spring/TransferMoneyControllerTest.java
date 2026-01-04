package org.lombold.blueprints.cleanarchitecture.frameworks.web.spring;

import org.junit.jupiter.api.Test;
import org.lombold.blueprints.cleanarchitecture.adapter.controllers.dto.TransferMoneyRequest;
import org.lombold.blueprints.cleanarchitecture.frameworks.persistency.jpa.AccountEntity;
import org.lombold.blueprints.cleanarchitecture.frameworks.persistency.jpa.JpaAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class TransferMoneyControllerTest {

    @Autowired
    private RestTestClient restTestClient;

    @Autowired(required = false)
    private JpaAccountRepository jpaAccountRepository;

    @Test
    void transferMoney_moneyTransferred() {
        final var sourceAccount = new AccountEntity("CH810023123213123", new BigDecimal(500), "CHF");
        final var targetAccount = new AccountEntity("CH81002312324464563", new BigDecimal(200), "CHF");
        jpaAccountRepository.saveAll(List.of(sourceAccount, targetAccount));

        restTestClient
                .post()
                .uri("/account/transfer")
                .body(new TransferMoneyRequest(sourceAccount.getIban(), targetAccount.getIban(), 100.0, "CHF"))
                .exchange()
                .expectStatus().isOk();

        assertThat(jpaAccountRepository.findByIban(sourceAccount.getIban()))
                .map(AccountEntity::getBalance)
                .hasValue(new BigDecimal("400.00"));
        assertThat(jpaAccountRepository.findByIban(targetAccount.getIban()))
                .map(AccountEntity::getBalance)
                .hasValue(new BigDecimal("300.00"));
    }
}

