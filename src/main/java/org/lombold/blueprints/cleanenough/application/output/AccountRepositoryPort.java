package org.lombold.blueprints.cleanenough.application.output;

import org.lombold.blueprints.cleanenough.domain.entities.Account;
import org.lombold.blueprints.cleanenough.domain.entities.IBAN;


public interface AccountRepositoryPort {
    Account save(Account account);

    Account findByIban(IBAN iban);
}
