package com.tenis.camisas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CamisaResponse {

    private Long id;
    private String nome;
    private String modelo;
    private Double preco;
    private boolean vendida;
}