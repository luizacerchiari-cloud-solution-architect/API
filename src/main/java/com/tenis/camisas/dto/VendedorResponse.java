package com.tenis.camisas.dto;

public record VendedorResponse(
        Long id,
        String nome,
        String cpf,
        String matricula
) {}
