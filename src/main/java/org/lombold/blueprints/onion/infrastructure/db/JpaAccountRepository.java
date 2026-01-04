package org.lombold.blueprints.onion.infrastructure.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByIban(String iban);
}
