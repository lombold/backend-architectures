package org.lombold.blueprints.cleanarchitecture.adapter.gateways;

import lombok.RequiredArgsConstructor;
import org.lombold.blueprints.cleanarchitecture.application.output.AccountRepository;
import org.lombold.blueprints.cleanarchitecture.domain.entities.Account;
import org.lombold.blueprints.cleanarchitecture.domain.entities.IBAN;

@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepository {
    private final AccountRepositoryGateway gateway;

    @Override
    public Account save(final Account account) {
        return gateway.save(account);
    }

    @Override
    public Account findByIban(final IBAN iban) {
        return gateway.findByIban(iban);
    }
}
