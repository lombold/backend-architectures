package org.lombold.blueprints.onion.core.application;

import org.lombold.blueprints.onion.core.domain.model.Account;
import org.lombold.blueprints.onion.core.domain.model.IBAN;

public interface AccountRepository {
    Account save(Account account);

    Account findByIban(IBAN iban);
}
