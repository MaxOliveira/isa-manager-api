package com.isa.manager.isa_manager_api.domain.Variation;

import com.isa.manager.isa_manager_api.domain.Product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="variacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Variation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    private String descricao;
    @ManyToOne
    @JoinColumn(name="id_produto", nullable = false)
    private Product product;
}
