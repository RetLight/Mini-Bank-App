package com.project.minibank.customer.domain.model;

import java.util.Objects;

public class Favorite {

    private static final int MAX_ALIAS_LENGTH = 50;
    private static final int MAX_ACCOUNT_NUMBER_LENGTH = 20;
    private static final int MAX_BANK_LENGTH = 50;
    private static final int MAX_HOLDER_LENGTH = 100;

    private final Long id;
    private final String alias;
    private final String accountNumber;
    private final String bank;
    private final String holder;
    private final Long customerId;

    public Favorite(Long id, String alias, String accountNumber, String bank, String holder, Long customerId) {
        this.id = id;
        this.alias = requireText(alias, "El alias", MAX_ALIAS_LENGTH);
        this.accountNumber = requireText(accountNumber, "El número de cuenta", MAX_ACCOUNT_NUMBER_LENGTH);
        if (!this.accountNumber.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("El número de cuenta solo debe contener dígitos");
        }
        this.bank = requireText(bank, "El banco", MAX_BANK_LENGTH);
        this.holder = requireText(holder, "El titular", MAX_HOLDER_LENGTH);
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

    private static String requireText(String value, String field, int maxLength) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " es obligatorio");
        }
        String trimmed = value.trim();
        if (trimmed.length() > maxLength) {
            throw new IllegalArgumentException(field + " no puede superar " + maxLength + " caracteres");
        }
        return trimmed;
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
