package org.lombold.blueprints.onion.ui.web;

public record TransferMoneyRequest(
        String sourceIban,
        String targetIban,
        double amount,
        String currency
) {
}
