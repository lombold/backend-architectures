package org.lombold.blueprints.hexagonal.adapter.primary.controller;

public record TransferMoneyRequest(
        String sourceIban,
        String targetIban,
        double amount,
        String currency
) {
}
