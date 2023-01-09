package com.daymeijroos.iacspring.Product;

import com.daymeijroos.iacspring.Product.ProductDAO;
import com.daymeijroos.iacspring.Product.ProductDTO;
import com.daymeijroos.iacspring.Product.ProductFilter;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.Product.ProductMapper;
import com.daymeijroos.iacspring.Product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductDAO productDAO;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductDAO productDAO, ProductMapper productMapper) {
        this.productDAO = productDAO;
        this.productMapper = productMapper;
    }

    public List<ProductDTO> get(String id, String name, ProductFilter filter) throws ResourceNotFoundException {
        if (id != null) {
            List<ProductDTO> products = new ArrayList<>();
            Product product = productDAO.getById(id);
            products.add(productMapper.fromEntityToDTO(product));
            return products;
        }
        if (name != null) {
            List<ProductDTO> products = new ArrayList<>();
            Product product = productDAO.getByName(name);
            products.add(productMapper.fromEntityToDTO(product));
            return products;
        }
        if (filter != null) {
            return productDAO.getByFilter(filter).stream().map(productMapper::fromEntityToDTO).collect(Collectors.toList());
        }
        return productDAO.getAll().stream().map(productMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO post(ProductDTO productDTO) {
        Product product = productMapper.fromDTOToEntity(productDTO);
        Product savedProduct = this.productDAO.saveToDatabase(product);
        return productMapper.fromEntityToDTO(savedProduct);
    }

    public ProductDTO update(ProductDTO productDTO) throws ResourceNotFoundException {
        Product product = productMapper.fromDTOToEntity(productDTO);
        Product updatedProduct = productDAO.update(product.getId(), product);
        return productMapper.fromEntityToDTO(updatedProduct);
    }

    public ProductDTO patch(ProductDTO productDTO) throws ResourceNotFoundException {
        Product product = productMapper.fromDTOToEntity(productDTO);
        Product updatedProduct = productDAO.update(product.getId(), product);
        return productMapper.fromEntityToDTO(updatedProduct);
    }

    public void delete(String id) throws ResourceNotFoundException {
        productDAO.delete(id);
    }
}
