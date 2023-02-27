package com.daymeijroos.iacspring.Admin;

import com.daymeijroos.iacspring.Category.CategoryDTO;
import com.daymeijroos.iacspring.Category.CategoryService;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(value = "/isAdmin")
    public ResponseEntity<Boolean> getCategory(Authentication authentication) {
        return ResponseEntity.ok(adminService.checkForUserId(authentication.getName()));
    }
}
