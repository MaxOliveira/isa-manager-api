package com.isa.manager.isa_manager_api.repositories;

import com.isa.manager.isa_manager_api.domain.Variation.Variation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VariationRepository extends JpaRepository<Variation, Long> {

    List<Variation> findByProductId(Integer productId);
}
