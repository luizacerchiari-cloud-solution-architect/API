package com.tenis.camisas.repository;

import com.tenis.camisas.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query("""
            SELECT v.vendedor.id, v.vendedor.nome, COUNT(v.id), COALESCE(SUM(v.valorTotal), 0)
            FROM Venda v
            WHERE v.dataVenda BETWEEN :inicio AND :fim
            GROUP BY v.vendedor.id, v.vendedor.nome
            ORDER BY COALESCE(SUM(v.valorTotal), 0) DESC, COUNT(v.id) DESC
            """)
    List<Object[]> rankingVendedores(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);

    boolean existsByVendedorId(Long vendedorId);
}
