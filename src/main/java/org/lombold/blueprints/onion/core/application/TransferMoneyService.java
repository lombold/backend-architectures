package org.lombold.blueprints.onion.core.application;

import lombok.RequiredArgsConstructor;
import org.lombold.blueprints.onion.core.domain.model.IBAN;
import org.lombold.blueprints.onion.core.domain.model.Money;
import org.lombold.blueprints.onion.core.domain.service.TransferMoneyDomainService;

@RequiredArgsConstructor
public class TransferMoneyService implements TransferMoneyUseCase {
    private final AccountRepository accountRepository;
    private final TransferMoneyDomainService transferMoneyDomainService = new TransferMoneyDomainService();

    @Override
    public void transfer(final IBAN from, final IBAN to, final Money amount) {
        final var fromAccount = accountRepository.findByIban(from);
        final var toAccount = accountRepository.findByIban(to);

        transferMoneyDomainService.transfer(fromAccount, toAccount, amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}
