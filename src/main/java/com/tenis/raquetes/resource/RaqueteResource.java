package com.tenis.raquetes.resource;

import com.tenis.raquetes.dto.RaqueteRequest;
import com.tenis.raquetes.dto.RaqueteResponse;
import com.tenis.raquetes.service.RaqueteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/raquetes")
@RequiredArgsConstructor
public class RaqueteResource {

    private final RaqueteService raqueteService;

    @PostMapping
    public ResponseEntity<RaqueteResponse> criar(@Valid @RequestBody RaqueteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(raqueteService.criar(request));
    }

    @GetMapping
    public List<RaqueteResponse> listar() {
        return raqueteService.listar();
    }
}
