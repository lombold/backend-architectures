package org.lombold.blueprints.onion.ui.web;

import lombok.RequiredArgsConstructor;
import org.lombold.blueprints.onion.core.application.TransferMoneyUseCase;
import org.lombold.blueprints.onion.core.domain.model.IBAN;
import org.lombold.blueprints.onion.core.domain.model.Money;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@Validated
@RequiredArgsConstructor
public class TransferMoneyController {

    private final TransferMoneyUseCase transferMoneyUseCase;

    @PostMapping("/transfer")
    public void transferMoney(@RequestBody final TransferMoneyRequest request) {
        transferMoneyUseCase.transfer(
                new IBAN(request.sourceIban()),
                new IBAN(request.targetIban()),
                new Money(request.amount(), request.currency())
        );
    }
}
