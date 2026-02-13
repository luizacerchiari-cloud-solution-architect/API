package com.tenis.raquetes.dto;

public record RaqueteResponse(
        Long id,
        String nome,
        String modelo,
        Double preco,
        boolean vendida
) {}
