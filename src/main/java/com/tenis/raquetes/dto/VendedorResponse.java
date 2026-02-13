package com.tenis.raquetes.dto;

public record VendedorResponse(
        Long id,
        String nome,
        String cpf,
        String matricula
) {}
