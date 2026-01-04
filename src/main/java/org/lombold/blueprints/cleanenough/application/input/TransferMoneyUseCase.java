package org.lombold.blueprints.cleanenough.application.input;

import org.lombold.blueprints.cleanenough.domain.entities.IBAN;
import org.lombold.blueprints.cleanenough.domain.entities.Money;

public interface TransferMoneyUseCase {
    void invoke(IBAN from, IBAN to, Money amount);
}
