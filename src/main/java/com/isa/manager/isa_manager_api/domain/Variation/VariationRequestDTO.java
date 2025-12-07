package com.isa.manager.isa_manager_api.domain.Variation;

import java.util.List;

public record VariationRequestDTO(
        Long id,
        String nome,
        String descricao,
        Long productId,
        List<AtributeVariationDTO> atributeVariationDTOList) {
}
