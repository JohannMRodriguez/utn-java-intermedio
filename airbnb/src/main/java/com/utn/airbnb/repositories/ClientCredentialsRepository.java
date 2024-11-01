package com.utn.airbnb.repositories;

import com.utn.airbnb.entities.ClientCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientCredentialsRepository  extends JpaRepository<ClientCredentials, Long> {
    Optional<ClientCredentials> findByUsername(String username);
}
