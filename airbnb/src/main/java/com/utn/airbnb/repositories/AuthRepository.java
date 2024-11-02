package com.utn.airbnb.repositories;

import com.utn.airbnb.entities.ClientCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<ClientCredentials, Long> {
    Optional<ClientCredentials> findByUsername(String username);
}
