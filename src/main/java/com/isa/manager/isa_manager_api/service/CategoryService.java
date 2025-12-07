package com.isa.manager.isa_manager_api.service;

import com.isa.manager.isa_manager_api.domain.Category.Category;
import com.isa.manager.isa_manager_api.domain.Category.CategoryRequestDTO;
import com.isa.manager.isa_manager_api.domain.Category.CategoryResponseDTO;
import com.isa.manager.isa_manager_api.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(CategoryRequestDTO categoryRequestDTO){
        if (categoryRepository.existsByNome(categoryRequestDTO.name())) {
            throw new IllegalArgumentException("Já existe uma categoria com esse nome");
        }
        Category category = new Category();
        category.setNome(categoryRequestDTO.name());
        category.setDescricao(categoryRequestDTO.description());
        categoryRepository.save(category);
        return category;
    }

    public List<CategoryResponseDTO> categoryList() {
        return categoryRepository.findAll().stream().map(category -> new CategoryResponseDTO(
                category.getId(),
                category.getNome(),
                category.getDescricao())).toList();
    }

    public CategoryResponseDTO findCategoryByName(String categoryName) {
        Category category =  categoryRepository.findByCategoryNome(categoryName).isPresent() ?
                categoryRepository.findByCategoryNome(categoryName).get() : null;

        if(category == null) {
            return null;
        }
        return new CategoryResponseDTO(
                category.getId(),
                category.getNome(),
                category.getDescricao());
    }

    public void deleteCategoryByName(String categoryName) {
        categoryRepository.delete(categoryRepository.findByCategoryNome(categoryName)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada!")));
    }

    public void deleteCategoryById(Long categoryId) {
        categoryRepository.delete(categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada!")));
    }

    public Category updateCategory(CategoryRequestDTO categoryRequestDTO){
        Category category =  categoryRepository.findById(categoryRequestDTO.id())
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada!"));

        if (!categoryRequestDTO.name().equalsIgnoreCase(category.getNome())) {
            throw new IllegalArgumentException("Já existe uma categoria com esse nome");
        }
        Category newCategory = new Category();
        newCategory.setId(categoryRequestDTO.id());
        newCategory.setNome(categoryRequestDTO.name());
        newCategory.setDescricao(categoryRequestDTO.description());
        categoryRepository.save(newCategory);
        return category;
    }

}
