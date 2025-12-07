package com.isa.manager.isa_manager_api.domain.Product;

import com.isa.manager.isa_manager_api.domain.Variation.VariationRequestDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record ProductRequestDTO(Long id, String nome, String descricao, BigDecimal precoCusto, BigDecimal precoVenda,
                                boolean ativo, Long categoria, Date dataCriacao, List<VariationRequestDTO> variationRequestDTOList) {
}
