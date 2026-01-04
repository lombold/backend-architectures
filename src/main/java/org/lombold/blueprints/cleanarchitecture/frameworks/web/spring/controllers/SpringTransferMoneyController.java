package org.lombold.blueprints.cleanarchitecture.frameworks.web.spring.controllers;

import lombok.RequiredArgsConstructor;
import org.lombold.blueprints.cleanarchitecture.adapter.controllers.TransferMoneyController;
import org.lombold.blueprints.cleanarchitecture.adapter.controllers.dto.TransferMoneyRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@Validated
@RequiredArgsConstructor
public class SpringTransferMoneyController {

    private final TransferMoneyController transferMoneyController;

    @PostMapping("/transfer")
    public void transferMoney(@RequestBody final TransferMoneyRequest request) {
        transferMoneyController.transferMoney(request);
    }
}
