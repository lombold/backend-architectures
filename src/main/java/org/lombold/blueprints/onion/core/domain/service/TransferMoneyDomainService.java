package org.lombold.blueprints.onion.core.domain.service;

import org.lombold.blueprints.onion.core.domain.model.Account;
import org.lombold.blueprints.onion.core.domain.model.Money;

public final class TransferMoneyDomainService {
    public void transfer(final Account from, final Account to, final Money amount) {
        if (from.getIban().equals(to.getIban())) {
            throw new IllegalArgumentException("Cannot transfer to same account");
        }

        from.withdraw(amount);
        to.deposit(amount);
    }
}
