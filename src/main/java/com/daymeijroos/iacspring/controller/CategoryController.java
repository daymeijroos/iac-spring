package com.daymeijroos.iacspring.controller;

import com.daymeijroos.iacspring.dto.CategoryDTO;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.service.CategoryService;

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
@RequestMapping(value = "/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> getCategory(@RequestParam(required = false) String id) {
        try {
            return ResponseEntity.ok(categoryService.get(id));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
