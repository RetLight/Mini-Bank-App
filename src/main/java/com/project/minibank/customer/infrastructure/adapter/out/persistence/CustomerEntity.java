package com.project.minibank.customer.infrastructure.adapter.out.persistence;

import com.project.minibank.customer.domain.model.CustomerStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_documento")
    private String documentType;

    @Column(name = "numero_documento")
    private String documentNumber;

    @Column(name = "nombres")
    private String firstNames;

    @Column(name = "apellidos")
    private String lastNames;

    private String email;

    @Column(name = "telefono")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private CustomerStatus status;

    @Column(name = "fecha_registro")
    private LocalDateTime registeredAt;

    @OneToMany(mappedBy = "customer")
    private List<FavoriteEntity> favorites = new ArrayList<>();

    public CustomerEntity() {
    }

    public CustomerEntity(String documentType, String documentNumber, String firstNames, String lastNames,
                          String email, String phone, CustomerStatus status, LocalDateTime registeredAt) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.registeredAt = registeredAt;
    }

    public Long getId() {
        return id;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getFirstNames() {
        return firstNames;
    }

    public String getLastNames() {
        return lastNames;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public List<FavoriteEntity> getFavorites() {
        return favorites;
    }
}
