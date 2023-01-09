package com.daymeijroos.iacspring.controller;

import com.daymeijroos.iacspring.dto.ProductDTO;
import com.daymeijroos.iacspring.enums.ProductFilter;
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
    public ResponseEntity<List<ProductDTO>> getProduct(@RequestParam(required = false) String id, @RequestParam(required = false) String name, @RequestParam(required = false) ProductFilter filter) {
        try {
            return ResponseEntity.ok(productService.get(id, name, filter));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
