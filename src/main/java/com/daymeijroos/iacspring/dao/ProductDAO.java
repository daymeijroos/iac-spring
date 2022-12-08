package com.daymeijroos.iacspring.dao;

import com.daymeijroos.iacspring.dao.repository.ProductRepository;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class ProductDAO implements DAO<Product>{
    private final ProductRepository productRepository;

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

    @Override
    public Product saveToDatabase(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product update(String id, Product productRequest) throws ResourceNotFoundException {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Building", id));

        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImageURL(productRequest.getImageURL());
        return productRepository.save(product);
    }

    @Override
    public void delete(String id) throws ResourceNotFoundException {
        productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Building", id));

        this.productRepository.deleteById(id);
    }
}
