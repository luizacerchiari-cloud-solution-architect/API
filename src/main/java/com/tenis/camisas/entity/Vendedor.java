package com.tenis.camisas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "vendedores",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "cpf"),
                @UniqueConstraint(columnNames = "matricula")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String matricula;
}
