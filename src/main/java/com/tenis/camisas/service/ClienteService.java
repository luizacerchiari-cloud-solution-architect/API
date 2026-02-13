package com.tenis.camisas.service;

import com.tenis.camisas.dto.ClienteRequest;
import com.tenis.camisas.dto.ClienteResponse;
import com.tenis.camisas.entity.Cliente;
import com.tenis.camisas.exception.NaoEncontradoException;
import com.tenis.camisas.mapper.Mapper;
import com.tenis.camisas.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteResponse criar(ClienteRequest request) {
        Cliente c = Mapper.toEntity(request);
        return Mapper.toResponse(clienteRepository.save(c));
    }

    public List<ClienteResponse> listar() {
        return clienteRepository.findAll().stream().map(Mapper::toResponse).toList();
    }

    public Cliente buscarEntidade(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Cliente não encontrado"));
    }
}
