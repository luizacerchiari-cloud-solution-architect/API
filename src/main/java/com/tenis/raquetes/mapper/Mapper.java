package com.tenis.raquetes.mapper;

import com.tenis.raquetes.dto.*;
import com.tenis.raquetes.entity.*;

import java.util.List;

public class Mapper {

    public static Raquete toEntity(RaqueteRequest r) {
        return Raquete.builder()
                .nome(r.nome())
                .modelo(r.modelo())
                .preco(r.preco())
                .vendida(false)
                .build();
    }

    public static RaqueteResponse toResponse(Raquete r) {
        return new RaqueteResponse(r.getId(), r.getNome(), r.getModelo(), r.getPreco(), r.isVendida());
    }

    public static Cliente toEntity(ClienteRequest c) {
        return Cliente.builder()
                .nome(c.nome())
                .elegivelDesconto(c.elegivelDesconto())
                .build();
    }

    public static ClienteResponse toResponse(Cliente c) {
        return new ClienteResponse(c.getId(), c.getNome(), c.isElegivelDesconto());
    }

    public static Vendedor toEntity(VendedorRequest v) {
        return Vendedor.builder()
                .nome(v.nome())
                .cpf(v.cpf())
                .matricula(v.matricula())
                .build();
    }

    public static VendedorResponse toResponse(Vendedor v) {
        return new VendedorResponse(v.getId(), v.getNome(), v.getCpf(), v.getMatricula());
    }

    public static VendaResponse toResponse(Venda venda) {
        List<RaqueteResponse> raquetes = venda.getRaquetes().stream().map(Mapper::toResponse).toList();
        return new VendaResponse(
                venda.getId(),
                venda.getDataVenda(),
                venda.getValorTotal(),
                toResponse(venda.getCliente()),
                toResponse(venda.getVendedor()),
                raquetes
        );
    }
}
