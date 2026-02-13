package com.tenis.camisas.repository;

import com.tenis.camisas.entity.Camisa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CamisaRepository extends JpaRepository<Camisa, Long> {}
