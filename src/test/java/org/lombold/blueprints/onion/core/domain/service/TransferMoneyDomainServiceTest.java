package org.lombold.blueprints.onion.core.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lombold.blueprints.onion.core.domain.model.Account;
import org.lombold.blueprints.onion.core.domain.model.IBAN;
import org.lombold.blueprints.onion.core.domain.model.Money;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransferMoneyDomainServiceTest {
    private TransferMoneyDomainService transferMoneyService;

    @BeforeEach
    void setUp() {
        transferMoneyService = new TransferMoneyDomainService();
    }

    @Test
    void transfer_positiveBalance_withdrawAndDepositCorrectly() {
        // Arrange
        final var from = new Account(new IBAN("CH1234567890"), new Money(1000, "CHF"));
        final var to = new Account(new IBAN("CH0987654321"), new Money(500, "CHF"));

        // Act
        transferMoneyService.transfer(from, to, new Money(200, "CHF"));

        // Assert
        assertEquals(800, from.getBalance().amount());
        assertEquals(700, to.getBalance().amount());
    }
}