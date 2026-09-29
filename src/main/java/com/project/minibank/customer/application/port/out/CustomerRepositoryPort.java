package com.project.minibank.customer.application.port.out;

import com.project.minibank.customer.domain.model.Customer;

import java.util.Optional;

public interface CustomerRepositoryPort {

    Optional<Customer> findById(Long id);
}
