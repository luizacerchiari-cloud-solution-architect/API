package com.tenis.raquetes.dto;

public record LoginResponse(
        String accessToken,
        String tokenType
) {}
