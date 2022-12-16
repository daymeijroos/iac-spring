package com.daymeijroos.iacspring.service;

import com.daymeijroos.iacspring.dao.ProductDAO;
import com.daymeijroos.iacspring.dto.ProductDTO;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.mapper.ProductMapper;
import com.daymeijroos.iacspring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<ProductDTO> getAllProducts() {
        return productDAO.getAll().stream().map(productMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(String id) throws ResourceNotFoundException {
        Product product = productDAO.getById(id);
        return productMapper.fromEntityToDTO(product);
    }

    public void WriteProduct()
}
