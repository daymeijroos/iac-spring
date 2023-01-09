package com.daymeijroos.iacspring.Product;

import com.daymeijroos.iacspring.shared.DAO;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDAO implements DAO<Product> {
    private final ProductRepository productRepository;

    @Autowired
    public ProductDAO(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getById(String id) throws ResourceNotFoundException {
        Optional<Product> result = productRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new ResourceNotFoundException("Product", id);
        }
    }

    public Product getByName(String name) throws ResourceNotFoundException {
        Optional<Product> result = productRepository.findProductByName(name);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new ResourceNotFoundException("Product", name);
        }
    }

    public List<Product> getByFilter(ProductFilter filter) {
        return productRepository.findProductsByFilter(filter);
    }

    @Override
    public Product saveToDatabase(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product update(String id, Product productRequest) throws ResourceNotFoundException {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
        if (productRequest.getName() != null) {product.setName(productRequest.getName());}
        if (productRequest.getDescription() != null) {product.setDescription(productRequest.getDescription());}
        if (productRequest.getPrice() != null) {product.setPrice(productRequest.getPrice());}
        if (productRequest.getImageUrl() != null) {product.setImageUrl(productRequest.getImageUrl());}
        return productRepository.save(product);
    }

    @Override
    public void delete(String id) throws ResourceNotFoundException {
        productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
        this.productRepository.deleteById(id);
    }
}
