package com.daymeijroos.iacspring.controller;

import com.daymeijroos.iacspring.dto.ProductDTO;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<ProductDTO>> getProduct(@RequestParam(required = false) String id, @AuthenticationPrincipal OidcUser principal) {
        try {
            return ResponseEntity.ok(productService.get(id));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<ProductDTO> postProduct(@RequestBody @Valid ProductDTO productDTO) {
        return ResponseEntity.ok(productService.post(productDTO));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<ProductDTO> updateProduct(@RequestParam String id, @RequestBody ProductDTO productDTO) {
        try {
            productDTO.setId(id);
            return ResponseEntity.ok().body(productService.update(productDTO));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PatchMapping(path = "", consumes = "application/json-patch+json")
    public ResponseEntity<ProductDTO> patchProduct(@RequestParam String id, @RequestBody ProductDTO productDTO) {
        productDTO.setId(id);
        try {
            return ResponseEntity.ok(productService.patch(productDTO));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteProduct(@RequestParam String id) {
        try {
            this.productService.delete(id);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
