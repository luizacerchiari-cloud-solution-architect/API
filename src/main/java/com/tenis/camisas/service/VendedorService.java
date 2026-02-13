package com.tenis.camisas.service;

import com.tenis.camisas.dto.VendedorRequest;
import com.tenis.camisas.dto.VendedorResponse;
import com.tenis.camisas.entity.Vendedor;
import com.tenis.camisas.exception.NaoEncontradoException;
import com.tenis.camisas.exception.RegraNegocioException;
import com.tenis.camisas.mapper.Mapper;
import com.tenis.camisas.repository.VendaRepository;
import com.tenis.camisas.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VendedorService {

    private final VendedorRepository vendedorRepository;
    private final VendaRepository vendaRepository;

    public VendedorResponse criar(VendedorRequest request) {
        vendedorRepository.findByCpf(request.cpf()).ifPresent(v -> { throw new RegraNegocioException("CPF já cadastrado"); });
        vendedorRepository.findByMatricula(request.matricula()).ifPresent(v -> { throw new RegraNegocioException("Matrícula já cadastrada"); });

        Vendedor v = Mapper.toEntity(request);
        return Mapper.toResponse(vendedorRepository.save(v));
    }

    public List<VendedorResponse> listar() {
        return vendedorRepository.findAll().stream().map(Mapper::toResponse).toList();
    }

    public void excluir(Long id) {
        if (vendaRepository.existsByVendedorId(id)) {
            throw new RegraNegocioException("Não é possível excluir vendedor que já realizou vendas");
        }
        if (!vendedorRepository.existsById(id)) {
            throw new NaoEncontradoException("Vendedor não encontrado");
        }
        vendedorRepository.deleteById(id);
    }

    public Vendedor buscarEntidade(Long id) {
        return vendedorRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Vendedor não encontrado"));
    }
}
