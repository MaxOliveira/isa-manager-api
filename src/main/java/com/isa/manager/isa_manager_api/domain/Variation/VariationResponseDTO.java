package com.isa.manager.isa_manager_api.domain.Variation;

import com.isa.manager.isa_manager_api.domain.Stock.StockResponseDTO;

public record VariationResponseDTO(
        Integer id,
        String variationNome,
        String variationDescricao,
        String atributeVariation,
        StockResponseDTO stock
) {

}
