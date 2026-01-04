package org.lombold.blueprints.cleanarchitecture.adapter.controllers;

import lombok.RequiredArgsConstructor;
import org.lombold.blueprints.cleanarchitecture.adapter.controllers.dto.TransferMoneyRequest;
import org.lombold.blueprints.cleanarchitecture.application.TransferMoneyService;
import org.lombold.blueprints.cleanarchitecture.domain.entities.IBAN;
import org.lombold.blueprints.cleanarchitecture.domain.entities.Money;

@RequiredArgsConstructor
public class TransferMoneyController {

    private final TransferMoneyService transferMoneyUseCase;

    public void transferMoney(final TransferMoneyRequest request) {
        transferMoneyUseCase.invoke(
                new IBAN(request.sourceIban()),
                new IBAN(request.targetIban()),
                new Money(request.amount(), request.currency())
        );
    }
}
