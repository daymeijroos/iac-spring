package com.daymeijroos.iacspring.Admin;

import com.daymeijroos.iacspring.Category.CategoryDTO;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.Category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/admin/category")
public class AdminCategoryController {
    private final CategoryService categoryService;
    private final AdminService adminService;

    @Autowired
    public AdminCategoryController(CategoryService categoryService, AdminService adminService) {
        this.categoryService = categoryService;
        this.adminService = adminService;
    }

    @PostMapping(value = "")
    public ResponseEntity<CategoryDTO> postCategory(@RequestBody @Valid CategoryDTO categoryDTO, Authentication authentication) {
        if (!this.adminService.checkForUserId(authentication.getName())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to add categories");
        }
        return ResponseEntity.ok().body(this.categoryService.post(categoryDTO));
    }

    @PutMapping(value = "")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestParam String id, @RequestBody CategoryDTO categoryDTO, Authentication authentication) {
        if (!this.adminService.checkForUserId(authentication.getName())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to replace categories");
        }
        categoryDTO.setId(id);
        try {
            return ResponseEntity.ok().body(this.categoryService.update(categoryDTO));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PatchMapping(path = "")
    public ResponseEntity<CategoryDTO> patchCategory(@RequestParam String id, @RequestBody CategoryDTO categoryDTO, Authentication authentication) {
        if (!this.adminService.checkForUserId(authentication.getName())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to edit categories");
        }
        categoryDTO.setId(id);
        try {
            return ResponseEntity.ok(this.categoryService.patch(categoryDTO));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping(value = "")
    public ResponseEntity deleteCategory(@RequestParam String id, Authentication authentication) {
        if (!this.adminService.checkForUserId(authentication.getName())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not authorized to remove categories");
        }
        try {
            this.categoryService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
