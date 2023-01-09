package com.daymeijroos.iacspring.ShippingDetails;

import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<ShippingDetailsDTO>> getShippingDetails(@RequestParam(required = false) String id) {
        if (id != null) {
            try {
                return ResponseEntity.ok(shippingDetailsService.getById(id));
            } catch (ResourceNotFoundException e) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, e.getMessage(), e);
            }
        }

        return ResponseEntity.ok(shippingDetailsService.get());
    }
}
