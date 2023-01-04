package com.daymeijroos.iacspring.mapper;

import com.daymeijroos.iacspring.dao.CategoryDAO;
import com.daymeijroos.iacspring.dto.ProductDTO;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.model.Product;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductDTO> {
    private final CategoryDAO categoryDAO;

    @Autowired
    public ProductMapper(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public Product fromDTOToEntity(@Nonnull ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImageUrl(productDTO.getImageUrl());
        try {
            product.setCategory(categoryDAO.getById(productDTO.getCategoryId()));
        } catch (ResourceNotFoundException ignored) {}
        return product;
    }

    @Override
    public ProductDTO fromEntityToDTO(@Nonnull Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageUrl(product.getImageUrl());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setCategoryName(product.getCategory().getName());
        return productDTO;
    }
}
