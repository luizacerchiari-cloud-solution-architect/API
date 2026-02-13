package com.tenis.raquetes.resource;

import com.tenis.raquetes.dto.RankingVendedorResponse;
import com.tenis.raquetes.dto.VendaRequest;
import com.tenis.raquetes.dto.VendaResponse;
import com.tenis.raquetes.service.VendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vendas")
@RequiredArgsConstructor
public class VendaResource {

    private final VendaService vendaService;

    @PostMapping
    public ResponseEntity<VendaResponse> registrar(@Valid @RequestBody VendaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaService.registrar(request));
    }

    @GetMapping
    public List<VendaResponse> listar() {
        return vendaService.listar();
    }

    @GetMapping("/ranking/vendedores")
    public List<RankingVendedorResponse> ranking(
            @RequestParam("dataInicio") LocalDate dataInicio,
            @RequestParam("dataFim") LocalDate dataFim
    ) {
        return vendaService.ranking(dataInicio, dataFim);
    }
}
