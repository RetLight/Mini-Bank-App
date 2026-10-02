package com.project.minibank.auth.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "rol")
    private String role;

    @Column(name = "intentos_fallidos")
    private int failedAttempts;

    @Column(name = "bloqueado")
    private boolean blocked;

    @Column(name = "cliente_id")
    private Long customerId;

    public UserEntity() {
    }

    public UserEntity(String username, String passwordHash, String role, Long customerId) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getRole() {
        return role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
