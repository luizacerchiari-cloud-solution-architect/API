package com.tenis.raquetes.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(
        @NotBlank String nome,
        boolean elegivelDesconto
) {}
