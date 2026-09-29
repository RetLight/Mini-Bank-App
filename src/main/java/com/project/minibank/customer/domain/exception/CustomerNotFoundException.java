package com.project.minibank.customer.domain.exception;

import com.project.minibank.exception.ResourceNotFoundException;

public class CustomerNotFoundException extends ResourceNotFoundException {

    public CustomerNotFoundException(Long id) {
        super("Cliente no encontrado: " + id);
    }
}
