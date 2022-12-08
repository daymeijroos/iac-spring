package com.daymeijroos.iacspring.controller;

import com.daymeijroos.iacspring.dao.ProductDAO;
import com.daymeijroos.iacspring.dto.ProductDTO;

import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.mapper.ProductMapper;
import com.daymeijroos.iacspring.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/product")
public class ProductController {
    private final ProductDAO productDAO;
    private final ProductMapper productMapper;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ProductDTO> getAllProducts() {
        return productDAO.getAll().stream().map(productMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductDTO> getProductByID(@PathVariable String id) {
        try {
            Product product = this.productDAO.getById(id);
            ProductDTO productResponse = productMapper.fromEntityToDTO(product);
            return ResponseEntity.ok().body(productResponse);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        Product productRequested = productMapper.fromDTOToEntity(productDTO);
        Product product = this.productDAO.saveToDatabase(productRequested);
        ProductDTO productResponse = productMapper.fromEntityToDTO(product);
        return ResponseEntity.ok(productResponse);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String id, @RequestBody ProductDTO productDTO) {
        productDTO.setId(id);
        Product productRequest = productMapper.fromDTOToEntity(productDTO);
        try {
            Product product = productDAO.update(id, productRequest);
            ProductDTO productResponse = productMapper.fromEntityToDTO(product);
            return ResponseEntity.ok().body(productResponse);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Product> patchProduct(@PathVariable String id, @RequestBody JsonPatch patch) {
        try {
            Product product = this.productDAO.getById(id);
            Product productPatched = applyPatchToProduct(patch, product);
            this.productDAO.saveToDatabase(productPatched);
            return ResponseEntity.ok(productPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    private Product applyPatchToProduct(JsonPatch patch, Product targetProduct) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetProduct, JsonNode.class));
        return objectMapper.treeToValue(patched, Product.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        try {
            this.productDAO.delete(id);
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
