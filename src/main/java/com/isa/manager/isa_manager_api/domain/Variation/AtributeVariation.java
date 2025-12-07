package com.isa.manager.isa_manager_api.domain.Variation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="variacao_atributo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtributeVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome_atributo;
    private String valor_atributo;
    @ManyToOne
    @JoinColumn(name="id_variacao", nullable = false)
    private Variation variation;
}
