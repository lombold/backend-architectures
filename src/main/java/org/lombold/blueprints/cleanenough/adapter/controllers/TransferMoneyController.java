package org.lombold.blueprints.cleanenough.adapter.controllers;

import lombok.RequiredArgsConstructor;
import org.lombold.blueprints.cleanenough.adapter.controllers.dto.TransferMoneyRequest;
import org.lombold.blueprints.cleanenough.application.TransferMoneyService;
import org.lombold.blueprints.cleanenough.domain.entities.IBAN;
import org.lombold.blueprints.cleanenough.domain.entities.Money;
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

    private final TransferMoneyService transferMoneyUseCase;

    @PostMapping("/transfer")
    public void transferMoney(@RequestBody final TransferMoneyRequest request) {
        transferMoneyUseCase.invoke(
                new IBAN(request.sourceIban()),
                new IBAN(request.targetIban()),
                new Money(request.amount(), request.currency())
        );
    }
}
