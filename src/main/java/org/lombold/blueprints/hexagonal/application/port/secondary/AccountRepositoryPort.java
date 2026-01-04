package org.lombold.blueprints.hexagonal.application.port.secondary;

import org.lombold.blueprints.hexagonal.application.model.Account;
import org.lombold.blueprints.hexagonal.application.model.IBAN;

public interface AccountRepositoryPort {
    Account save(Account account);

    Account findByIban(IBAN iban);
}
