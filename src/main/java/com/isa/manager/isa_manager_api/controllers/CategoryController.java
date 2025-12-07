package com.isa.manager.isa_manager_api.controllers;

import com.isa.manager.isa_manager_api.domain.Category.Category;
import com.isa.manager.isa_manager_api.domain.Category.CategoryRequestDTO;
import com.isa.manager.isa_manager_api.domain.Category.CategoryResponseDTO;
import com.isa.manager.isa_manager_api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        Category newCategory = categoryService.createCategory(categoryRequestDTO);
        return ResponseEntity.ok(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        List<CategoryResponseDTO> categoryResponseDTOList = categoryService.categoryList();
        return ResponseEntity.ok(categoryResponseDTOList);
    }

    @GetMapping("/name")
    public ResponseEntity<CategoryResponseDTO> findByName(@RequestParam String categoryName) {
        CategoryResponseDTO categoryResponseDTOList = categoryService.findCategoryByName(categoryName);
        return ResponseEntity.ok(categoryResponseDTOList);
    }

    @DeleteMapping("/deleteByName/{categoryName}")
    public ResponseEntity<Void> deteleCategoryByName(@PathVariable String categoryName) {
        categoryService.deleteCategoryByName(categoryName);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteById/{categoryId}")
    public ResponseEntity<Void> deteleCategoryById(@PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        Category newCategory = categoryService.updateCategory(categoryRequestDTO);
        return ResponseEntity.ok(newCategory);
    }
}
