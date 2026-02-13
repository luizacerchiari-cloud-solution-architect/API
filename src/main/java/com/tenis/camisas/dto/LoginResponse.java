package com.tenis.camisas.dto;

public record LoginResponse(
        String accessToken,
        String tokenType
) {}
