package com.tenis.raquetes.dto;

public record RankingVendedorResponse(
        Long vendedorId,
        String nomeVendedor,
        Long totalVendas,
        Double valorTotalVendido
) {}
