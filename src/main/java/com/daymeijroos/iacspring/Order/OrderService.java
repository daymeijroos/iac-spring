package com.daymeijroos.iacspring.Order;

import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderDAO orderDAO;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderDAO OrderDAO, OrderMapper OrderMapper) {
        this.orderDAO = OrderDAO;
        this.orderMapper = OrderMapper;
    }

    public List<OrderDTOOut> get() {
        return orderDAO.getAll().stream().map(orderMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTOOut> getById(String id) throws ResourceNotFoundException {
        List<OrderDTOOut> orders = new ArrayList<>();
        Order order = orderDAO.getById(id);
        orders.add(orderMapper.fromEntityToDTO(order));
        return orders;
    }

    public OrderDTOOut post(OrderDTOIn orderDTO) {
        Order order = orderMapper.fromDTOToEntity(orderDTO);
        Order savedOrder = orderDAO.saveToDatabase(order);
        return orderMapper.fromEntityToDTO(savedOrder);
    }

    public OrderDTOOut update(OrderDTOIn orderDTO) throws ResourceNotFoundException {
        Order Order = orderMapper.fromDTOToEntity(orderDTO);
        Order updatedOrder = orderDAO.update(Order.getId(), Order);
        return orderMapper.fromEntityToDTO(updatedOrder);
    }

    public OrderDTOOut patch(OrderDTOIn orderDTO) throws ResourceNotFoundException {
        Order order = orderMapper.fromDTOToEntity(orderDTO);
        Order updatedOrder = orderDAO.update(order.getId(), order);
        return orderMapper.fromEntityToDTO(updatedOrder);
    }

    public void delete(String id) throws ResourceNotFoundException {
        orderDAO.delete(id);
    }
}
