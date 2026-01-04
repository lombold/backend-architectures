package org.lombold.blueprints.hexagonal.adapter.secondary.persistency;

import org.lombold.blueprints.hexagonal.application.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.ERROR
)
public interface HexagonalAccountMapper {
    @Mapping(source = "iban.iban", target = "iban")
    @Mapping(source = "balance.amount", target = "balance")
    @Mapping(source = "balance.currency", target = "currency")
    AccountEntity toEntity(Account account);

    @Mapping(source = "iban", target = "iban.iban")
    @Mapping(source = "balance", target = "balance.amount")
    @Mapping(source = "balance", target = "balance.currency")
    Account toDomain(AccountEntity entity);
}
