package org.lombold.blueprints.hexagonal.application.port.primary;

import org.lombold.blueprints.hexagonal.application.model.IBAN;
import org.lombold.blueprints.hexagonal.application.model.Money;

public interface TransferMoneyUseCase {
    void transfer(final IBAN from, final IBAN to, final Money amount);
}
