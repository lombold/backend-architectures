package org.lombold.blueprints.cleanarchitecture.domain.service;

import org.lombold.blueprints.cleanarchitecture.domain.entities.Account;
import org.lombold.blueprints.cleanarchitecture.domain.entities.Money;

public final class TransferMoneyDomainService {
    public void transfer(final Account from, final Account to, final Money amount) {
        if (from.getIban().equals(to.getIban())) {
            throw new IllegalArgumentException("Cannot transfer to same account");
        }

        from.withdraw(amount);
        to.deposit(amount);
    }
}
