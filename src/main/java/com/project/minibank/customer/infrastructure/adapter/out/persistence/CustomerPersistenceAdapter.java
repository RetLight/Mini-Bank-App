package com.project.minibank.customer.infrastructure.adapter.out.persistence;

import com.project.minibank.customer.application.port.out.CustomerRepositoryPort;
import com.project.minibank.customer.domain.model.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerPersistenceAdapter implements CustomerRepositoryPort {

    private final CustomerJpaRepository customerJpaRepository;

    public CustomerPersistenceAdapter(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerJpaRepository.findWithFavoritesById(id).map(FavoritePersistenceMapper::toDomain);
    }
}
