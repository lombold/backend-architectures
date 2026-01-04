package org.lombold.blueprints.hexagonal.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Account {
    private final IBAN iban;
    private Money balance;

    public void withdraw(final Money amount) {
        this.balance = new Money(this.balance.amount() - amount.amount(), this.balance.currency());
    }

    public void deposit(final Money amount) {
        this.balance = new Money(this.balance.amount() + amount.amount(), this.balance.currency());
    }
}
