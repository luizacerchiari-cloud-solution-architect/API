package com.tenis.raquetes.resource;

import com.tenis.raquetes.dto.VendedorRequest;
import com.tenis.raquetes.dto.VendedorResponse;
import com.tenis.raquetes.service.VendedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendedores")
@RequiredArgsConstructor
public class VendedorResource {

    private final VendedorService vendedorService;

    @PostMapping
    public ResponseEntity<VendedorResponse> criar(@Valid @RequestBody VendedorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vendedorService.criar(request));
    }

    @GetMapping
    public List<VendedorResponse> listar() {
        return vendedorService.listar();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        vendedorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
