package com.project.minibank.customer.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "favoritos")
public class FavoriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alias;

    @Column(name = "numero_cuenta")
    private String accountNumber;

    @Column(name = "banco")
    private String bank;

    @Column(name = "titular")
    private String holder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private CustomerEntity customer;

    public FavoriteEntity() {
    }

    public FavoriteEntity(String alias, String accountNumber, String bank, String holder, CustomerEntity customer) {
        this.alias = alias;
        this.accountNumber = accountNumber;
        this.bank = bank;
        this.holder = holder;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CustomerEntity getCustomer() {
        return customer;
    }
}
