package org.lombold.blueprints.hexagonal.adapter.secondary.persistency;

import lombok.RequiredArgsConstructor;
import org.lombold.blueprints.hexagonal.application.model.Account;
import org.lombold.blueprints.hexagonal.application.model.IBAN;
import org.lombold.blueprints.hexagonal.application.port.secondary.AccountRepositoryPort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountRepositoryPortAdapter implements AccountRepositoryPort {
    private final JpaAccountRepository repository;
    private final HexagonalAccountMapper mapper;

    @Override
    public Account save(final Account account) {
        return mapper.toDomain(repository.save(mapper.toEntity(account)));
    }

    @Override
    public Account findByIban(final IBAN iban) {
        return repository.findByIban(iban.iban()).map(mapper::toDomain).orElseThrow(() -> new NotFoundException(iban.iban()));
    }
}
