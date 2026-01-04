package org.lombold.blueprints.cleanenough.application;

import org.lombold.blueprints.cleanenough.application.input.TransferMoneyUseCase;
import org.lombold.blueprints.cleanenough.application.output.AccountRepositoryPort;
import org.lombold.blueprints.cleanenough.domain.entities.IBAN;
import org.lombold.blueprints.cleanenough.domain.entities.Money;
import org.lombold.blueprints.cleanenough.domain.service.TransferMoneyDomainService;

public class TransferMoneyService implements TransferMoneyUseCase {

    private final AccountRepositoryPort accountRepositoryPort;
    private final TransferMoneyDomainService transferMoneyDomainService = new TransferMoneyDomainService();

    public TransferMoneyService(final AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public void invoke(final IBAN from, final IBAN to, final Money amount) {
        final var fromAccount = accountRepositoryPort.findByIban(from);
        final var toAccount = accountRepositoryPort.findByIban(to);

        transferMoneyDomainService.transfer(fromAccount, toAccount, amount);

        accountRepositoryPort.save(fromAccount);
        accountRepositoryPort.save(toAccount);
    }
}
