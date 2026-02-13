package com.tenis.camisas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CamisaRequest(
        @NotBlank String nome,
        @NotBlank String modelo,
        @NotNull @Positive Double preco
) {}
