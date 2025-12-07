package com.isa.manager.isa_manager_api.repositories;

import com.isa.manager.isa_manager_api.domain.Product.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = """
            SELECT DISTINCT p.*
            FROM produto p
            WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :productName, '%'))
            ORDER BY p.id;""", nativeQuery = true)
    List<Product> searchProductsByNameWithDetails(@Param("productName") String productName, Pageable pageable);
}
