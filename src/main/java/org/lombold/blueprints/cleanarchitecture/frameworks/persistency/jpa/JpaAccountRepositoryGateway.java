package org.lombold.blueprints.cleanarchitecture.frameworks.persistency.jpa;

import lombok.RequiredArgsConstructor;
import org.lombold.blueprints.cleanarchitecture.adapter.gateways.AccountRepositoryGateway;
import org.lombold.blueprints.cleanarchitecture.domain.entities.Account;
import org.lombold.blueprints.cleanarchitecture.domain.entities.IBAN;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaAccountRepositoryGateway implements AccountRepositoryGateway {
    private final JpaAccountRepository repository;
    private final CleanArchitectureAccountMapper mapper;

    @Override
    public Account save(final Account account) {
        return mapper.toDomain(repository.save(mapper.toEntity(account)));
    }

    @Override
    public Account findByIban(final IBAN iban) {
        return repository.findByIban(iban.iban()).map(mapper::toDomain).orElseThrow(() -> new NotFoundException(iban.iban()));
    }
}
