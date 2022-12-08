package com.daymeijroos.iacspring.mapper;

import com.daymeijroos.iacspring.dao.ProductDAO;
import com.daymeijroos.iacspring.dto.ProductDTO;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProductMapper implements Mapper<Product, ProductDTO> {
    private final ProductDAO productDAO;

    @Override
    public Product fromDTOToEntity(ProductDTO productDTO) {
        if (productDTO == null) {return null;}

        Product product = new Product();

        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImageURL(productDTO.getImageURL());

        return product;
    }

    @Override
    public ProductDTO fromEntityToDTO(Product product) {
        if (product == null) {return null;}

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageURL(product.getImageURL());

        return productDTO;
    }

    @Override
    public Product fromIdToEntity(String id) {
        if ( id == null ) {return null;}

        try {
            return productDAO.getById(id);
        } catch (ResourceNotFoundException e) {
            return null;
        }
    }
}
