package com.tenis.camisas.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record VendaRequest(
        @NotNull Long clienteId,
        @NotNull Long vendedorId,
        @NotEmpty List<Long> camisasIds
) {}
