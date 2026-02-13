package com.tenis.camisas.dto;

import jakarta.validation.constraints.NotBlank;

public record VendedorRequest(
        @NotBlank String nome,
        @NotBlank String cpf,
        @NotBlank String matricula
) {}
