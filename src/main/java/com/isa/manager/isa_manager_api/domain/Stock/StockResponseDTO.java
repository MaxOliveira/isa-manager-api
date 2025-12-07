package com.isa.manager.isa_manager_api.domain.Stock;

public record StockResponseDTO(
        Integer stockId,
        Integer quantidade,
        Integer estoqueMinimo,
        Integer estoqueMaximo,
        String localizacao
) {
}
