package org.lombold.blueprints.cleanenough.adapter.controllers.dto;

public record TransferMoneyRequest(
        String sourceIban,
        String targetIban,
        double amount,
        String currency
) {
}
