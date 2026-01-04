package org.lombold.blueprints.hexagonal.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lombold.blueprints.hexagonal.application.model.Account;
import org.lombold.blueprints.hexagonal.application.model.IBAN;
import org.lombold.blueprints.hexagonal.application.model.Money;
import org.lombold.blueprints.hexagonal.application.port.secondary.AccountRepositoryPort;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransferMoneyServiceTest {

    @Mock
    private AccountRepositoryPort accountRepositoryPort;

    @InjectMocks
    private TransferMoneyService transferMoneyService;

    @Test
    void transfer_positiveBalance_withdrawAndDepositCorrectly() {
        // Arrange
        final var from = new Account(new IBAN("DE1234567890"), new Money(1000, "CHF"));
        final var to = new Account(new IBAN("DE0987654321"), new Money(500, "CHF"));
        when(accountRepositoryPort.findByIban(from.getIban())).thenReturn(from);
        when(accountRepositoryPort.findByIban(to.getIban())).thenReturn(to);

        // Act
        transferMoneyService.transfer(from.getIban(), to.getIban(), new Money(200, "CHF"));

        // Assert
        assertEquals(800, from.getBalance().amount());
        assertEquals(700, to.getBalance().amount());
    }
}