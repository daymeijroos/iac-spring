package com.daymeijroos.iacspring.Order;

import com.daymeijroos.iacspring.shared.DAO;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class OrderDAO implements DAO<Order> {
    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }

    public List<Order> getByUserId(String id) throws ResourceNotFoundException {
        List<Order> result = orderRepository.findByUserId(id);
        if(!result.isEmpty()) {
            return result;
        }else {
            throw new ResourceNotFoundException("Order", id);
        }
    }

    @Override
    public Order getById(String id) throws ResourceNotFoundException {
        Optional<Order> result = orderRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("Order", id);
        }
    }

    @Override
    public Order saveToDatabase(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public Order update(String id, Order orderRequest) throws ResourceNotFoundException {
        Order order = this.orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", id));
        order.setPaymentStatus(orderRequest.getPaymentStatus());
        order.setShippingStatus(orderRequest.getShippingStatus());
        return orderRepository.save(order);
    }

    @Override
    public void delete(String id) throws ResourceNotFoundException {
        orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", id));
        this.orderRepository.deleteById(id);
    }
}
