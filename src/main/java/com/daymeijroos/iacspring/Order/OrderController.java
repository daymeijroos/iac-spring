package com.daymeijroos.iacspring.Order;

import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<OrderDTO>> getOrder(@RequestParam(required = false) String id) {
        if (id != null) {
            try {
                return ResponseEntity.ok(orderService.getById(id));
            } catch (ResourceNotFoundException e) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, e.getMessage(), e);
            }
        }
        return ResponseEntity.ok(orderService.get());
    }

    @PostMapping(value = "")
    public ResponseEntity<OrderDTO> postOrder(@RequestBody @Valid OrderDTO order, Authentication auth) {
        if (auth != null) {
            order.setUserId(auth.getName());
        }
        try {
            return ResponseEntity.ok(orderService.post(order));
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
