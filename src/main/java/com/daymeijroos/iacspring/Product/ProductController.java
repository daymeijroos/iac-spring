package com.daymeijroos.iacspring.Product;

import com.daymeijroos.iacspring.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDTO>> getProduct(@RequestParam(required = false) String id, @RequestParam(required = false) String name, @RequestParam(required = false) ProductFilter filter) {
        if (id != null) {
            try {
                return ResponseEntity.ok(productService.getById(id));
            } catch (ResourceNotFoundException e) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, e.getMessage(), e);
            }
        }
        if (name != null) {
            try {
                return ResponseEntity.ok(productService.getByName(name));
            } catch (ResourceNotFoundException e) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, e.getMessage(), e);
            }
        }
        if (filter != null) {
            return ResponseEntity.ok(productService.getByFilter(filter));
        }
        return ResponseEntity.ok(productService.get());
    }
}
