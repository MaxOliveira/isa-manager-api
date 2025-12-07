package com.isa.manager.isa_manager_api.service;

import com.isa.manager.isa_manager_api.domain.Product.Product;
import com.isa.manager.isa_manager_api.domain.Product.ProductRequestDTO;
import com.isa.manager.isa_manager_api.domain.Product.ProductResponseDTO;
import com.isa.manager.isa_manager_api.domain.Variation.AtributeVariation;
import com.isa.manager.isa_manager_api.domain.Variation.Variation;
import com.isa.manager.isa_manager_api.domain.Variation.VariationResponseDTO;
import com.isa.manager.isa_manager_api.repositories.AtributeVariationRepository;
import com.isa.manager.isa_manager_api.repositories.CategoryRepository;
import com.isa.manager.isa_manager_api.repositories.ProductRepository;
import com.isa.manager.isa_manager_api.repositories.VariationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VariationRepository variationRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AtributeVariationRepository atributeVariationRepository;

    @Transactional
    public void createProduct(ProductRequestDTO productRequestDTO) {
        // Criar produto
        Product newProduct = new Product();
        newProduct.setNome(productRequestDTO.nome());
        newProduct.setDescricao(productRequestDTO.descricao());
        newProduct.setPrecoCusto(productRequestDTO.precoCusto());
        newProduct.setPrecoVenda(productRequestDTO.precoVenda());
        newProduct.setAtivo(productRequestDTO.ativo());
        newProduct.setDataCriacao(productRequestDTO.dataCriacao());

        // Buscar categoria somente uma vez
        categoryRepository.findById(productRequestDTO.categoria())
                .ifPresent(newProduct::setCategory);

        productRepository.save(newProduct);

        // Criar variações
        if (!productRequestDTO.variationRequestDTOList().isEmpty()) {
            productRequestDTO.variationRequestDTOList().forEach(variationDTO -> {
                Variation newVariation = new Variation();
                newVariation.setNome(variationDTO.nome());
                newVariation.setDescricao(variationDTO.descricao());
                newVariation.setProduct(newProduct);
                variationRepository.save(newVariation);

                // Criar os atributos da variação
                variationDTO.atributeVariationDTOList().forEach(attrDTO -> {
                    AtributeVariation newAttr = new AtributeVariation();
                    newAttr.setVariation(newVariation);
                    newAttr.setNome_atributo(attrDTO.nome_atributo());
                    newAttr.setValor_atributo(attrDTO.valor_atributo());
                    atributeVariationRepository.save(newAttr);
                });
            });
        }
    }

    public void deleteProduct(List<ProductRequestDTO> productRequestDTOList) {
        List<Product> productList = productRequestDTOList.stream().map(productRequestDTO ->
                productRepository.findById(productRequestDTO.id())
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + productRequestDTO.id()))).toList();

        productList.stream()
                .flatMap(product -> product.getVariationsList().stream())
                .map(variation -> atributeVariationRepository.findByVariationId(variation.getId()))
                .flatMap(List::stream)
                .forEach(atributeVariationRepository::delete);

        productRepository.deleteAll(productList);
    }

    public Page<ProductResponseDTO> searchProducts(String productName, Pageable pageable) {
         List<Product> products = productRepository.searchProductsByNameWithDetails(productName, pageable);

         if(products.isEmpty()) {
             return new PageImpl<>(Collections.emptyList(), pageable, 0);
         }

        List<ProductResponseDTO> productsResponseDTO = this.convertToProductToDTO(products);
        return new PageImpl<>(productsResponseDTO, pageable, productsResponseDTO.size());
    }

    private List<ProductResponseDTO> convertToProductToDTO(List<Product> products) {
        return products.stream()
                    .map(product -> new ProductResponseDTO(
                            product.getId(),
                            product.getNome(),
                            product.getDescricao(),
                            product.getPrecoVenda(),
                            product.getPrecoCusto(),
                            true,
                            product.getCategory(),
                            this.getVariationList(product.getId())
                    ))
                    .toList();
    }

    public List<VariationResponseDTO> getVariationList(Integer productId) {
        List<Variation> variationList = variationRepository.findByProductId(productId);
        return this.convertToVariationToDTO(variationList);
    }

    private List<VariationResponseDTO> convertToVariationToDTO(List<Variation> variations) {
        return variations.stream()
                        .map(variation -> new VariationResponseDTO(
                                variation.getId(),
                                variation.getNome(),
                                variation.getDescricao(),
                                atributeVariationRepository.getAtributeValue(variation.getId()),
                                null
                        ))
                        .toList();
    }


}
