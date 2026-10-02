package com.project.minibank.customer.domain.model;

import java.util.Objects;

public class Favorite {

    private final Long id;
    private final String alias;
    private final String accountNumber;
    private final String bank;
    private final String holder;
    private final Long customerId;

    public Favorite(Long id, String alias, String accountNumber, String bank, String holder, Long customerId) {
        if (alias == null || alias.isBlank()) {
            throw new IllegalArgumentException("El alias es obligatorio");
        }
        if (accountNumber == null || !accountNumber.matches("\\d+")) {
            throw new IllegalArgumentException("El número de cuenta solo debe contener dígitos");
        }
        if (bank == null || bank.isBlank()) {
            throw new IllegalArgumentException("El banco es obligatorio");
        }
        if (holder == null || holder.isBlank()) {
            throw new IllegalArgumentException("El titular es obligatorio");
        }
        this.id = id;
        this.alias = alias;
        this.accountNumber = accountNumber;
        this.bank = bank;
        this.holder = holder;
        this.customerId = Objects.requireNonNull(customerId, "El cliente es obligatorio");
    }

    public static Favorite create(String alias, String accountNumber, String bank, String holder, Long customerId) {
        return new Favorite(null, alias, accountNumber, bank, holder, customerId);
    }

    public boolean isSameAccountAs(Favorite other) {
        return accountNumber.equals(other.accountNumber) && bank.equalsIgnoreCase(other.bank);
    }

    public boolean hasSameAliasAs(Favorite other) {
        return alias.equalsIgnoreCase(other.alias);
    }

    public Long getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBank() {
        return bank;
    }

    public String getHolder() {
        return holder;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
