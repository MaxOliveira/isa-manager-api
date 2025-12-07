package com.isa.manager.isa_manager_api.controllers;

import com.isa.manager.isa_manager_api.domain.Product.ProductRequestDTO;
import com.isa.manager.isa_manager_api.domain.Product.ProductResponseDTO;
import com.isa.manager.isa_manager_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductRequestDTO productRequestDTO){
        productService.createProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@RequestBody List<ProductRequestDTO> productRequestDTOList) {
        productService.deleteProduct(productRequestDTOList);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponseDTO>> searchProducts(  @RequestParam String productName,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.searchProducts(productName, pageable));
    }
}
