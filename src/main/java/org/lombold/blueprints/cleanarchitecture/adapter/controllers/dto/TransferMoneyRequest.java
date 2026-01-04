package org.lombold.blueprints.cleanarchitecture.adapter.controllers.dto;

public record TransferMoneyRequest(
        String sourceIban,
        String targetIban,
        double amount,
        String currency
) {
}
