package com.tenis.camisas.service;

import com.tenis.camisas.dto.CamisaRequest;
import com.tenis.camisas.dto.CamisaResponse;
import com.tenis.camisas.entity.Camisa;
import com.tenis.camisas.exception.NaoEncontradoException;
import com.tenis.camisas.mapper.Mapper;
import com.tenis.camisas.repository.CamisaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CamisaService {

    private final CamisaRepository camisaRepository;

    public CamisaResponse criar(CamisaRequest request) {
        Camisa camisa = Mapper.toEntity(request);
        Camisa salva = camisaRepository.save(camisa);
        return Mapper.toResponse(salva);
    }

    public List<CamisaResponse> listar() {
        return camisaRepository.findAll()
                .stream()
                .map(Mapper::toResponse)
                .toList();
    }

    public CamisaResponse buscar(Long id) {
        Camisa camisa = camisaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Camisa não encontrada"));

        return Mapper.toResponse(camisa);
    }

    public Camisa buscarEntidade(Long id) {
        return camisaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Camisa não encontrada"));
    }
}