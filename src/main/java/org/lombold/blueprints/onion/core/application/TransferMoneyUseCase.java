package org.lombold.blueprints.onion.core.application;

import org.lombold.blueprints.onion.core.domain.model.IBAN;
import org.lombold.blueprints.onion.core.domain.model.Money;

public interface TransferMoneyUseCase {
    void transfer(final IBAN from, final IBAN to, final Money amount);
}
