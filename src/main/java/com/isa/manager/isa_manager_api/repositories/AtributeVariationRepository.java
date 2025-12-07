package com.isa.manager.isa_manager_api.repositories;

import com.isa.manager.isa_manager_api.domain.Variation.AtributeVariation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AtributeVariationRepository extends JpaRepository<AtributeVariation, Long> {
    @Query(value = """
            SELECT
                string_agg(
                    a.nome_atributo || ': ' || a.valor_atributo,
                    ';' ORDER BY a.nome_atributo
                ) AS atributos_variacao
            FROM produto p
            LEFT JOIN variacao v ON v.id_produto = p.id
            LEFT JOIN variacao_atributo a ON a.id_variacao = v.id
            WHERE a.id_variacao = :idVariacao
            GROUP BY p.id,  p.nome, v.id, v.nome
            ORDER BY p.id, v.id;""", nativeQuery = true)
    String getAtributeValue(@Param("idVariacao") Integer idVariacao);

    List<AtributeVariation> findByVariationId(Integer variationId);
}
