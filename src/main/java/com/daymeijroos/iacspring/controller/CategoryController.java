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
    public ResponseEntity<List<CategoryDTO>> getCategory(@RequestParam(required = false) String id, @AuthenticationPrincipal OidcUser principal) {
        try {
            return ResponseEntity.ok(categoryService.get(id));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<CategoryDTO> postCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.post(categoryDTO));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<CategoryDTO> updateCategory(@RequestParam String id, @RequestBody CategoryDTO categoryDTO) {
        try {
            categoryDTO.setId(id);
            return ResponseEntity.ok().body(categoryService.update(categoryDTO));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PatchMapping(path = "", consumes = "application/json-patch+json")
    public ResponseEntity<CategoryDTO> patchCategory(@RequestParam String id, @RequestBody CategoryDTO categoryDTO) {
        categoryDTO.setId(id);
        try {
            return ResponseEntity.ok(categoryService.patch(categoryDTO));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCategory(@RequestParam String id) {
        try {
            this.categoryService.delete(id);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
