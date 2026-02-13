package com.tenis.camisas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vendas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_venda", nullable = false)
    private LocalDateTime dataVenda;

    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    @ManyToMany
    @JoinTable(
            name = "venda_camisas",
            joinColumns = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "camisa_id")
    )
    private List<Camisa> camisas;

    @PrePersist
    public void prePersist() {
        if (this.dataVenda == null) this.dataVenda = LocalDateTime.now();
    }
}
