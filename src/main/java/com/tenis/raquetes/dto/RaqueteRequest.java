package com.tenis.raquetes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RaqueteRequest(
        @NotBlank String nome,
        @NotBlank String modelo,
        @NotNull @Positive Double preco
) {}
