package org.lombold.blueprints.onion.ui.web;

import org.junit.jupiter.api.Test;
import org.lombold.blueprints.onion.infrastructure.db.AccountEntity;
import org.lombold.blueprints.onion.infrastructure.db.JpaAccountRepository;
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

    @Autowired
    private JpaAccountRepository jpaAccountRepository;

    @Test
    void transferMoney_moneyTransferred() {
        final var first = new AccountEntity("CH810023123213123", new BigDecimal(500), "CHF");
        final var second = new AccountEntity("CH81002312324464563", new BigDecimal(200), "CHF");
        jpaAccountRepository.saveAll(List.of(first, second));

        restTestClient
                .post()
                .uri("/account/transfer")
                .body(new TransferMoneyRequest(first.getIban(), second.getIban(), 100.0, "CHF"))
                .exchange()
                .expectStatus().isOk();

        assertThat(jpaAccountRepository.findByIban(first.getIban()))
                .map(AccountEntity::getBalance)
                .hasValue(new BigDecimal("400.00"));
        assertThat(jpaAccountRepository.findByIban(second.getIban()))
                .map(AccountEntity::getBalance)
                .hasValue(new BigDecimal("300.00"));
    }
}
