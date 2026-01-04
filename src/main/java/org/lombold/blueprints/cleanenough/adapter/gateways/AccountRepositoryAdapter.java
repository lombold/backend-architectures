package org.lombold.blueprints.cleanenough.adapter.gateways;

import lombok.RequiredArgsConstructor;
import org.lombold.blueprints.cleanenough.application.output.AccountRepositoryPort;
import org.lombold.blueprints.cleanenough.domain.entities.Account;
import org.lombold.blueprints.cleanenough.domain.entities.IBAN;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepositoryPort {
    private final JpaAccountRepository repository;
    private final CleanEnoughAccountMapper mapper;

    @Override
    public Account save(final Account account) {
        return mapper.toDomain(repository.save(mapper.toEntity(account)));
    }

    @Override
    public Account findByIban(final IBAN iban) {
        return repository.findByIban(iban.iban()).map(mapper::toDomain).orElseThrow(() -> new NotFoundException(iban.iban()));
    }
}
