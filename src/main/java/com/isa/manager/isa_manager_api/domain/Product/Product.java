package com.isa.manager.isa_manager_api.domain.Product;

import com.isa.manager.isa_manager_api.domain.Category.Category;
import com.isa.manager.isa_manager_api.domain.Variation.Variation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal precoCusto;
    private BigDecimal precoVenda;
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean ativo;
    private Date dataCriacao;
    @ManyToOne
    @JoinColumn(name="id_categoria")
    private Category category;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Variation> variationsList;
}
