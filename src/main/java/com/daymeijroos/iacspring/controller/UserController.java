package com.daymeijroos.iacspring.controller;

import com.daymeijroos.iacspring.dto.UserDTO;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getProducts(@RequestParam(required = false) String id, @RequestParam(required = false) String email) {
        try {
            return ResponseEntity.ok(userService.get(id, email));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> postUser(@RequestBody @Valid UserDTO userDTO) {
        return ResponseEntity.ok(userService.post(userDTO));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> updateUser(@RequestParam String id, @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        try {
            return ResponseEntity.ok().body(userService.update(userDTO));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PatchMapping(path = "", consumes = "application/json-patch+json")
    public ResponseEntity<UserDTO> patchUser(@RequestParam String id, @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        try {
            return ResponseEntity.ok(userService.patch(userDTO));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@RequestParam String id) {
        try {
            this.userService.delete(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}