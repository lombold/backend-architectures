package org.lombold.blueprints.cleanarchitecture.application.output;

import org.lombold.blueprints.cleanarchitecture.domain.entities.Account;
import org.lombold.blueprints.cleanarchitecture.domain.entities.IBAN;


public interface AccountRepository {
    Account save(Account account);

    Account findByIban(IBAN iban);
}
