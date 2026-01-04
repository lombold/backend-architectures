package org.lombold.blueprints.cleanarchitecture.application;

import org.lombold.blueprints.cleanarchitecture.application.input.TransferMoneyUseCase;
import org.lombold.blueprints.cleanarchitecture.application.output.AccountRepository;
import org.lombold.blueprints.cleanarchitecture.domain.entities.IBAN;
import org.lombold.blueprints.cleanarchitecture.domain.entities.Money;
import org.lombold.blueprints.cleanarchitecture.domain.service.TransferMoneyDomainService;

public class TransferMoneyService implements TransferMoneyUseCase {

    private final AccountRepository accountRepository;
    private final TransferMoneyDomainService transferMoneyDomainService = new TransferMoneyDomainService();

    public TransferMoneyService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void invoke(final IBAN from, final IBAN to, final Money amount) {
        final var fromAccount = accountRepository.findByIban(from);
        final var toAccount = accountRepository.findByIban(to);

        transferMoneyDomainService.transfer(fromAccount, toAccount, amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}
