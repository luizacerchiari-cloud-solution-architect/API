package com.tenis.raquetes.service;

import com.tenis.raquetes.dto.VendedorRequest;
import com.tenis.raquetes.dto.VendedorResponse;
import com.tenis.raquetes.entity.Vendedor;
import com.tenis.raquetes.exception.NaoEncontradoException;
import com.tenis.raquetes.exception.RegraNegocioException;
import com.tenis.raquetes.mapper.Mapper;
import com.tenis.raquetes.repository.VendaRepository;
import com.tenis.raquetes.repository.VendedorRepository;
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
