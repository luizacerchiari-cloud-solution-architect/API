package com.tenis.raquetes.resource;

import com.tenis.raquetes.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/relatorios")
@RequiredArgsConstructor
public class RelatorioResource {

    private final RelatorioService relatorioService;

    @GetMapping(value = "/vendas.xlsx")
    public ResponseEntity<InputStreamResource> relatorioVendas() throws IOException {
        ByteArrayInputStream in = relatorioService.relatorioVendasExcel();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }
}
