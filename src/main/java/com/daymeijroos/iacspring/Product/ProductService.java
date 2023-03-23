package com.daymeijroos.iacspring.Product;

import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
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

    public List<ProductDTO> get() {
        return productDAO.getAll().stream().map(productMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    List<ProductDTO>  getById(String id) throws ResourceNotFoundException {
        List<ProductDTO> products = new ArrayList<>();
        Product product = productDAO.getById(id);
        products.add(productMapper.fromEntityToDTO(product));
        return products;
    }

    List<ProductDTO>  getByName(String name) throws ResourceNotFoundException {
        List<ProductDTO> products = new ArrayList<>();
        Product product = productDAO.getByName(name);
        products.add(productMapper.fromEntityToDTO(product));
        return products;
    }

    List<ProductDTO>  getByFilter(ProductFilter filter) {
        return productDAO.getByFilter(filter).stream().map(productMapper::fromEntityToDTO).collect(Collectors.toList());
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
