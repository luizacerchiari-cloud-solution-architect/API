package com.tenis.raquetes.service;

import com.tenis.raquetes.dto.RaqueteRequest;
import com.tenis.raquetes.dto.RaqueteResponse;
import com.tenis.raquetes.entity.Raquete;
import com.tenis.raquetes.exception.NaoEncontradoException;
import com.tenis.raquetes.mapper.Mapper;
import com.tenis.raquetes.repository.RaqueteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RaqueteService {

    private final RaqueteRepository raqueteRepository;

    public RaqueteResponse criar(RaqueteRequest request) {
        Raquete raquete = Mapper.toEntity(request);
        return Mapper.toResponse(raqueteRepository.save(raquete));
    }

    public List<RaqueteResponse> listar() {
        return raqueteRepository.findAll().stream().map(Mapper::toResponse).toList();
    }

    public Raquete buscarEntidade(Long id) {
        return raqueteRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Raquete não encontrada"));
    }
}
