package com.tenis.raquetes.dto;

public record ClienteResponse(
        Long id,
        String nome,
        boolean elegivelDesconto
) {}
