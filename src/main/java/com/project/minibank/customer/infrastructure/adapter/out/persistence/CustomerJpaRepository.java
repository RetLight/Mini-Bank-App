package com.project.minibank.customer.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {

    @EntityGraph(attributePaths = "favorites")
    Optional<CustomerEntity> findWithFavoritesById(Long id);
}
