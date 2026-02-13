package com.tenis.camisas.repository;

import com.tenis.camisas.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    Optional<Vendedor> findByCpf(String cpf);
    Optional<Vendedor> findByMatricula(String matricula);
}
