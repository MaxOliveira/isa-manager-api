package com.isa.manager.isa_manager_api.repositories;

import com.isa.manager.isa_manager_api.domain.Category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNome(String nome);

    @Query("SELECT c FROM Category c WHERE c.nome = :categoryName")
    Optional<Category> findByCategoryNome(@Param("categoryName") String categoryName);
}