package com.daymeijroos.iacspring.ShippingDetails;

import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/shippingDetails")
public class ShippingDetailsController {
    private final ShippingDetailsService shippingDetailsService;

    @Autowired
    public ShippingDetailsController(ShippingDetailsService shippingDetailsService) {
        this.shippingDetailsService = shippingDetailsService;
    }

    @GetMapping(value = "")
    public ResponseEntity<ShippingDetailsDTO> getShippingDetails(Authentication authentication) {
        try {
            return ResponseEntity.ok(shippingDetailsService.getByUserId(authentication.getName()));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<ShippingDetailsDTO> postShippingDetails(ShippingDetailsDTO shippingDetailsDTO, Authentication authentication) {
        shippingDetailsDTO.setUserId(authentication.getName());
        return ResponseEntity.ok(shippingDetailsService.post(shippingDetailsDTO));
    }
}
