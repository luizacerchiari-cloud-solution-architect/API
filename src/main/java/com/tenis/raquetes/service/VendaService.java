package com.tenis.raquetes.service;

import com.tenis.raquetes.dto.RankingVendedorResponse;
import com.tenis.raquetes.dto.VendaRequest;
import com.tenis.raquetes.dto.VendaResponse;
import com.tenis.raquetes.entity.Raquete;
import com.tenis.raquetes.entity.Venda;
import com.tenis.raquetes.exception.RegraNegocioException;
import com.tenis.raquetes.mapper.Mapper;
import com.tenis.raquetes.repository.RaqueteRepository;
import com.tenis.raquetes.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final RaqueteRepository raqueteRepository;

    private final ClienteService clienteService;
    private final VendedorService vendedorService;

    private final EstoqueExternoClient estoqueExternoClient;

    @Transactional
    public VendaResponse registrar(VendaRequest request) {

        var cliente = clienteService.buscarEntidade(request.clienteId());
        var vendedor = vendedorService.buscarEntidade(request.vendedorId());

        List<Raquete> raquetes = raqueteRepository.findAllById(request.raquetesIds());
        if (raquetes.size() != request.raquetesIds().size()) {
            throw new RegraNegocioException("Uma ou mais raquetes não foram encontradas");
        }

        for (Raquete r : raquetes) {
            if (r.isVendida()) {
                throw new RegraNegocioException("Raquete já vendida: id=" + r.getId());
            }
        }

        // Circuit Breaker em chamada externa
        for (Raquete r : raquetes) {
            boolean disponivel = estoqueExternoClient.verificarDisponibilidade(r.getId());
            if (!disponivel) {
                throw new RegraNegocioException("Raquete sem disponibilidade (estoque): id=" + r.getId());
            }
        }

        double total = raquetes.stream().mapToDouble(Raquete::getPreco).sum();

        if (cliente.isElegivelDesconto()) {
            total *= 0.8;
        }

        Venda venda = Venda.builder()
                .cliente(cliente)
                .vendedor(vendedor)
                .raquetes(raquetes)
                .valorTotal(total)
                .build();

        raquetes.forEach(r -> r.setVendida(true));

        Venda salva = vendaRepository.save(venda);
        return Mapper.toResponse(salva);
    }

    public List<VendaResponse> listar() {
        return vendaRepository.findAll().stream().map(Mapper::toResponse).toList();
    }

    public List<RankingVendedorResponse> ranking(LocalDate dataInicio, LocalDate dataFim) {
        LocalDateTime inicio = dataInicio.atStartOfDay();
        LocalDateTime fim = dataFim.atTime(23, 59, 59);

        return vendaRepository.rankingVendedores(inicio, fim).stream()
                .map(arr -> new RankingVendedorResponse(
                        (Long) arr[0],
                        (String) arr[1],
                        (Long) arr[2],
                        ((Number) arr[3]).doubleValue()
                ))
                .toList();
    }
}
