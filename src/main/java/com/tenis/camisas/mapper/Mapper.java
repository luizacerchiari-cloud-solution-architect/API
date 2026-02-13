package com.tenis.camisas.mapper;

import com.tenis.camisas.dto.*;
import com.tenis.camisas.entity.*;

import java.util.List;

public class Mapper {

    public static Camisa toEntity(CamisaRequest r) {
        return Camisa.builder()
                .nome(r.nome())
                .modelo(r.modelo())
                .preco(r.preco())
                .vendida(false)
                .build();
    }

    public static CamisaResponse toResponse(Camisa r) {
        return new CamisaResponse(
                r.getId(),
                r.getNome(),
                r.getModelo(),
                r.getPreco(),
                r.isVendida()
        );
    }

    public static Cliente toEntity(ClienteRequest c) {
        return Cliente.builder()
                .nome(c.nome())
                .elegivelDesconto(c.elegivelDesconto())
                .build();
    }

    public static ClienteResponse toResponse(Cliente c) {
        return new ClienteResponse(
                c.getId(),
                c.getNome(),
                c.isElegivelDesconto()
        );
    }

    public static Vendedor toEntity(VendedorRequest v) {
        return Vendedor.builder()
                .nome(v.nome())
                .cpf(v.cpf())
                .matricula(v.matricula())
                .build();
    }

    public static VendedorResponse toResponse(Vendedor v) {
        return new VendedorResponse(
                v.getId(),
                v.getNome(),
                v.getCpf(),
                v.getMatricula()
        );
    }

    public static VendaResponse toResponse(Venda venda) {
        List<CamisaResponse> camisas =
                venda.getCamisas()
                        .stream()
                        .map(Mapper::toResponse)
                        .toList();

        return new VendaResponse(
                venda.getId(),
                venda.getDataVenda(),
                venda.getValorTotal(),
                toResponse(venda.getCliente()),
                toResponse(venda.getVendedor()),
                camisas
        );
    }
}