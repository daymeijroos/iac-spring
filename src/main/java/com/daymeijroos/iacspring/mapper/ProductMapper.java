package com.daymeijroos.iacspring.mapper;

import com.daymeijroos.iacspring.dao.ProductDAO;
import com.daymeijroos.iacspring.dto.ProductDTO;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.model.Product;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductDTO> {
    @Override
    public Product fromDTOToEntity(@Nonnull ProductDTO productDTO) {
        Product product = new Product();

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImageURL(productDTO.getImageURL());

        return product;
    }

    @Override
    public ProductDTO fromEntityToDTO(@Nonnull Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageURL(product.getImageURL());

        return productDTO;
    }
}
