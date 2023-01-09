package com.daymeijroos.iacspring.controller;

import com.daymeijroos.iacspring.dto.CategoryDTO;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.service.AdminService;
import com.daymeijroos.iacspring.service.CategoryService;
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

    @RequestMapping(path = "", method = RequestMethod.PATCH, consumes = "application/json-patch+json")
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
    public ResponseEntity<String> deleteCategory(@RequestParam String id, Authentication authentication) {
        System.out.println("USER: " + adminService.checkForUserId(authentication.getName()));
        //try {
            //this.categoryService.delete(id);
            return ResponseEntity.ok("Category deleted successfully");
        //} catch (ResourceNotFoundException e) {
        //    throw new ResponseStatusException(
        //            HttpStatus.NOT_FOUND, e.getMessage(), e);
        //}
    }
}
