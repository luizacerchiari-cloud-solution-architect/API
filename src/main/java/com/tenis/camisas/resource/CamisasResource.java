package com.tenis.camisas.resource;

import com.tenis.camisas.dto.CamisaRequest;
import com.tenis.camisas.dto.CamisaResponse;
import com.tenis.camisas.service.CamisaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/camisas")
@RequiredArgsConstructor
public class CamisasResource {


    private final CamisaService camisaService;

    @PostMapping
    public ResponseEntity<CamisaResponse> criar(@Valid @RequestBody CamisaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(camisaService.criar(request));
    }

    @GetMapping
    public List<CamisaResponse> listar() {
        return camisaService.listar();
    }
}
