package com.tenis.camisas.service;

import com.tenis.camisas.dto.RankingVendedorResponse;
import com.tenis.camisas.dto.VendaRequest;
import com.tenis.camisas.dto.VendaResponse;
import com.tenis.camisas.entity.Camisa;
import com.tenis.camisas.entity.Venda;
import com.tenis.camisas.exception.RegraNegocioException;
import com.tenis.camisas.mapper.Mapper;
import com.tenis.camisas.repository.VendaRepository;
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
    private final com.tenis.camisas.repository.CamisaRepository CamisaRepository;

    private final ClienteService clienteService;
    private final VendedorService vendedorService;

    private final EstoqueExternoClient estoqueExternoClient;

    @Transactional
    public VendaResponse registrar(VendaRequest request) {

        var cliente = clienteService.buscarEntidade(request.clienteId());
        var vendedor = vendedorService.buscarEntidade(request.vendedorId());

        List<Camisa> camisas = CamisaRepository.findAllById(request.camisasIds());
        if (camisas.size() != request.camisasIds().size()) {
            throw new RegraNegocioException("Uma ou mais camisas não foram encontradas");
        }

        for (Camisa r : camisas) {
            if (r.isVendida()) {
                throw new RegraNegocioException("Camisa já vendida: id=" + r.getId());
            }
        }

        // Circuit Breaker em chamada externa
        for (Camisa r : camisas) {
            boolean disponivel = estoqueExternoClient.verificarDisponibilidade(r.getId());
            if (!disponivel) {
                throw new RegraNegocioException("Camisa sem disponibilidade (estoque): id=" + r.getId());
            }
        }

        double total = camisas.stream().mapToDouble(Camisa::getPreco).sum();

        if (cliente.isElegivelDesconto()) {
            total *= 0.8;
        }

        Venda venda = Venda.builder()
                .cliente(cliente)
                .vendedor(vendedor)
                .camisas(camisas)
                .valorTotal(total)
                .build();

        camisas.forEach(r -> r.setVendida(true));

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
