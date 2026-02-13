package com.tenis.camisas.dto;

import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull String username,
        @NotNull String password
) {}
