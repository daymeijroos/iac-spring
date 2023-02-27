package com.daymeijroos.iacspring.Admin;

import com.daymeijroos.iacspring.Product.ProductDTO;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.Product.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/admin/product")
public class AdminProductController {
    private final ProductService productService;
    private final AdminService adminService;

    @Autowired
    public AdminProductController(ProductService productService, AdminService adminService) {
        this.productService = productService;
        this.adminService = adminService;
    }

    @PostMapping(value = "")
    public ResponseEntity<ProductDTO> postProduct(@RequestBody @Valid ProductDTO productDTO, Authentication authentication) {
        if (!this.adminService.checkForUserId(authentication.getName())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to add products");
        }
        return ResponseEntity.ok(this.productService.post(productDTO));
    }

    @PutMapping(value = "")
    public ResponseEntity<ProductDTO> updateProduct(@RequestParam String id, @RequestBody ProductDTO productDTO, Authentication authentication) {
        if (!this.adminService.checkForUserId(authentication.getName())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to replace products");
        }
        productDTO.setId(id);
        try {
            return ResponseEntity.ok().body(this.productService.update(productDTO));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PatchMapping(value = "", consumes = "application/json-patch+json")
    public ResponseEntity<ProductDTO> patchProduct(@RequestParam String id, @RequestBody ProductDTO productDTO, Authentication authentication) {
        if (!this.adminService.checkForUserId(authentication.getName())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to edit products");
        }
        productDTO.setId(id);
        try {
            return ResponseEntity.ok(this.productService.patch(productDTO));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping(value = "")
    public ResponseEntity deleteProduct(@RequestParam String id, Authentication authentication) {
        if (!this.adminService.checkForUserId(authentication.getName())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to remove products");
        }
        try {
            this.productService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
