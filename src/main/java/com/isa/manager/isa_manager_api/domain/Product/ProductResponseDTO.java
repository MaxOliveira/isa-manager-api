package com.isa.manager.isa_manager_api.domain.Product;

import com.isa.manager.isa_manager_api.domain.Category.Category;
import com.isa.manager.isa_manager_api.domain.Variation.VariationResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponseDTO(
        Integer productId,
        String productNome,
        String productDescricao,
        BigDecimal precoCusto,
        BigDecimal precoVenda,
        boolean ativo,
        Category category,
        List<VariationResponseDTO> variationList
      ) {
}
