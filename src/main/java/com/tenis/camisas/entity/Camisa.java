package com.tenis.camisas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "camisas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Camisa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Double preco;

    @Builder.Default
    @Column(nullable = false)
    private boolean vendida = false;
}
