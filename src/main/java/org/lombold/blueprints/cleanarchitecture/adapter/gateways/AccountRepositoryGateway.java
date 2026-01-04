package org.lombold.blueprints.cleanarchitecture.adapter.gateways;

import org.lombold.blueprints.cleanarchitecture.domain.entities.Account;
import org.lombold.blueprints.cleanarchitecture.domain.entities.IBAN;

public interface AccountRepositoryGateway {
    Account save(Account account);

    Account findByIban(IBAN iban);
}
