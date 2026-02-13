package com.tenis.raquetes.dto;

import java.time.LocalDateTime;
import java.util.List;

public record VendaResponse(
        Long id,
        LocalDateTime dataVenda,
        Double valorTotal,
        ClienteResponse cliente,
        VendedorResponse vendedor,
        List<RaqueteResponse> raquetes
) {}
