package com.project.minibank.config;

import com.project.minibank.auth.infrastructure.adapter.out.persistence.UserEntity;
import com.project.minibank.auth.infrastructure.adapter.out.persistence.UserJpaRepository;
import com.project.minibank.customer.domain.model.CustomerStatus;
import com.project.minibank.customer.infrastructure.adapter.out.persistence.CustomerEntity;
import com.project.minibank.customer.infrastructure.adapter.out.persistence.CustomerJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Profile("dev")
public class DevDataInitializer implements CommandLineRunner {

    private final CustomerJpaRepository customerJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public DevDataInitializer(CustomerJpaRepository customerJpaRepository, UserJpaRepository userJpaRepository,
                              PasswordEncoder passwordEncoder) {
        this.customerJpaRepository = customerJpaRepository;
        this.userJpaRepository = userJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userJpaRepository.count() > 0) {
            return;
        }

        CustomerEntity ana = customerJpaRepository.save(new CustomerEntity("DNI", "10000001", "Ana", "Torres",
                "ana.torres@mail.com", "999111222", CustomerStatus.ACTIVE, LocalDateTime.now()));
        CustomerEntity luis = customerJpaRepository.save(new CustomerEntity("DNI", "10000002", "Luis", "Ramirez",
                "luis.ramirez@mail.com", "999333444", CustomerStatus.ACTIVE, LocalDateTime.now()));

        userJpaRepository.save(new UserEntity("demo", passwordEncoder.encode("demo123"), "CUSTOMER", ana.getId()));
        userJpaRepository.save(new UserEntity("admin", passwordEncoder.encode("admin123"), "ADMIN", luis.getId()));

        System.out.println("Datos de prueba cargados: usuarios demo y admin");
    }
}
