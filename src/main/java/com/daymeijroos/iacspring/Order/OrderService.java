package com.daymeijroos.iacspring.Order;

import com.daymeijroos.iacspring.ShippingDetails.ShippingDetailsDAO;
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
    private final ShippingDetailsDAO shippingDetailsDAO;
    private final LineItemDAO lineItemDAO;

    @Autowired
    public OrderService(OrderDAO OrderDAO, OrderMapper OrderMapper, ShippingDetailsDAO shippingDetailsDAO, LineItemDAO lineItemDAO) {
        this.orderDAO = OrderDAO;
        this.orderMapper = OrderMapper;
        this.shippingDetailsDAO = shippingDetailsDAO;
        this.lineItemDAO = lineItemDAO;
    }

    public List<OrderDTO> get() {
        return orderDAO.getAll().stream().map(orderMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getById(String id) throws ResourceNotFoundException {
        List<OrderDTO> orders = new ArrayList<>();
        Order order = orderDAO.getById(id);
        orders.add(orderMapper.fromEntityToDTO(order));
        return orders;
    }

    public OrderDTO post(OrderDTO orderDTO) throws ResourceNotFoundException {
        Order order = orderMapper.fromDTOToEntity(orderDTO);
        shippingDetailsDAO.saveToDatabase(order.getShippingDetails());
        order.getLineItems().forEach(lineItemDAO::saveToDatabase);
        Order savedOrder = orderDAO.saveToDatabase(order);
        return orderMapper.fromEntityToDTO(savedOrder);
    }

    public OrderDTO update(OrderDTO orderDTO) throws ResourceNotFoundException {
        Order Order = orderMapper.fromDTOToEntity(orderDTO);
        Order updatedOrder = orderDAO.update(Order.getId(), Order);
        return orderMapper.fromEntityToDTO(updatedOrder);
    }

    public OrderDTO patch(OrderDTO orderDTO) throws ResourceNotFoundException {
        Order order = orderMapper.fromDTOToEntity(orderDTO);
        Order updatedOrder = orderDAO.update(order.getId(), order);
        return orderMapper.fromEntityToDTO(updatedOrder);
    }

    public void delete(String id) throws ResourceNotFoundException {
        orderDAO.delete(id);
    }
}
