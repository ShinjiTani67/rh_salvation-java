package com.br.rhsalvation.fiap.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.rhsalvation.fiap.demo.entity.Rh;

import java.util.Optional;
import java.util.UUID;


public interface RhRepository extends JpaRepository<Rh, UUID>{

    Optional<Rh> findByUuid(UUID uuid);
    Optional<Rh> findByEmail(String email);

}
