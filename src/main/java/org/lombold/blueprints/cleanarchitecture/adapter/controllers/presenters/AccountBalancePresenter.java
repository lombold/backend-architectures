package org.lombold.blueprints.cleanarchitecture.adapter.controllers.presenters;

import lombok.Getter;
import org.lombold.blueprints.cleanarchitecture.application.output.OutputPort;
import org.lombold.blueprints.cleanarchitecture.domain.entities.Account;

@Getter
public class AccountBalancePresenter implements OutputPort<Account> {

    private String value;

    @Override
    public void present(final Account account) {
        value = account.getBalance().toString();
    }
}
