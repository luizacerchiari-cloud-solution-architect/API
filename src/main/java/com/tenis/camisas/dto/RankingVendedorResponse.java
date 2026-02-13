package com.tenis.camisas.dto;

public record RankingVendedorResponse(
        Long vendedorId,
        String nomeVendedor,
        Long totalVendas,
        Double valorTotalVendido
) {}
