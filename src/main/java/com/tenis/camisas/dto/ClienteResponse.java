package com.tenis.camisas.dto;

public record ClienteResponse(
        Long id,
        String nome,
        boolean elegivelDesconto
) {}
