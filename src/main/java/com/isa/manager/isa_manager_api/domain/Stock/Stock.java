package com.isa.manager.isa_manager_api.domain.Stock;

import com.isa.manager.isa_manager_api.domain.Variation.Variation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="estoque")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "id_variacao", nullable = false, unique = true)
    private Variation variacao;
    @Column(nullable = false)
    private Integer quantidade = 0;
    private Integer estoqueMinimo = 0;
    private Integer estoqueMaximo;

}
