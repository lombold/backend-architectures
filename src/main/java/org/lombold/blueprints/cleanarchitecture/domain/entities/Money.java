package org.lombold.blueprints.cleanarchitecture.domain.entities;

public record Money(double amount, String currency) {
    @Override
    public String toString() {
        return amount + " " + currency;
    }
}
