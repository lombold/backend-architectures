package org.lombold.blueprints.hexagonal.application.service;

import lombok.RequiredArgsConstructor;
import org.lombold.blueprints.hexagonal.application.model.IBAN;
import org.lombold.blueprints.hexagonal.application.model.Money;
import org.lombold.blueprints.hexagonal.application.port.primary.TransferMoneyUseCase;
import org.lombold.blueprints.hexagonal.application.port.secondary.AccountRepositoryPort;

@RequiredArgsConstructor
public class TransferMoneyService implements TransferMoneyUseCase {
    private final AccountRepositoryPort accountRepositoryPort;

    @Override
    public void transfer(final IBAN from, final IBAN to, final Money amount) {
        final var fromAccount = accountRepositoryPort.findByIban(from);
        final var toAccount = accountRepositoryPort.findByIban(to);

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        accountRepositoryPort.save(fromAccount);
        accountRepositoryPort.save(toAccount);
    }
}
