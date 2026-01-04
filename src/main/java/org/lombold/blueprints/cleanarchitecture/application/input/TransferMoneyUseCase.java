package org.lombold.blueprints.cleanarchitecture.application.input;

import org.lombold.blueprints.cleanarchitecture.domain.entities.IBAN;
import org.lombold.blueprints.cleanarchitecture.domain.entities.Money;

public interface TransferMoneyUseCase {
    void invoke(IBAN from, IBAN to, Money amount);
}
