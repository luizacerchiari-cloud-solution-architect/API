package com.tenis.raquetes.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Exemplo de integração externa (estoque / antifraude / precificação).
 * Se o serviço externo falhar, o Circuit Breaker abre e usamos fallback.
 */
@Service
public class EstoqueExternoClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @CircuitBreaker(name = "estoqueService", fallbackMethod = "fallbackDisponivel")
    public boolean verificarDisponibilidade(Long raqueteId) {
        // Endpoint fictício. Ajuste para o seu serviço real se existir.
        String url = "http://localhost:9999/estoque/raquetes/" + raqueteId;
        Boolean ok = restTemplate.getForObject(url, Boolean.class);
        return ok != null && ok;
    }

    public boolean fallbackDisponivel(Long raqueteId, Exception ex) {
        // Estratégia: permitir seguir quando externo falha (você pode inverter se preferir bloquear).
        return true;
    }
}
