package com.project.minibank.auth.infrastructure.security;

import com.project.minibank.auth.infrastructure.adapter.out.persistence.UserEntity;
import com.project.minibank.auth.infrastructure.adapter.out.persistence.UserJpaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TokenService {

    private final JwtEncoder encoder;
    private final UserJpaRepository userJpaRepository;

    @Value("${jwt.expiration-minutes}")
    private long expirationMinutes;

    public TokenService(JwtEncoder encoder, UserJpaRepository userJpaRepository) {
        this.encoder = encoder;
        this.userJpaRepository = userJpaRepository;
    }

    public String generate(String username) {
        UserEntity user = userJpaRepository.findByUsername(username).orElseThrow();
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("minibank-api")
                .subject(username)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(getExpirationSeconds()))
                .claim("roles", List.of(user.getRole()))
                .claim("customerId", user.getCustomerId())
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        return encoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }

    public long getExpirationSeconds() {
        return expirationMinutes * 60;
    }
}
