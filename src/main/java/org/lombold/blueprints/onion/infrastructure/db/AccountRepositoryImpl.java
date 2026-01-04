package org.lombold.blueprints.onion.infrastructure.db;

import lombok.RequiredArgsConstructor;
import org.lombold.blueprints.onion.core.application.AccountRepository;
import org.lombold.blueprints.onion.core.domain.model.Account;
import org.lombold.blueprints.onion.core.domain.model.IBAN;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final JpaAccountRepository repository;
    private final OnionAccountMapper mapper;

    @Override
    public Account save(final Account account) {
        return mapper.toDomain(repository.save(mapper.toEntity(account)));
    }

    @Override
    public Account findByIban(final IBAN iban) {
        return repository.findByIban(iban.iban()).map(mapper::toDomain).orElseThrow(() -> new NotFoundException(iban.iban()));
    }
}
