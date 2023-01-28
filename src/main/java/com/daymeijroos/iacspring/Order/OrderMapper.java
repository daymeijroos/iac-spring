package com.daymeijroos.iacspring.Order;

import com.daymeijroos.iacspring.Product.ProductDAO;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.Product.ProductMapper;
import com.daymeijroos.iacspring.ShippingDetails.ShippingDetailsMapper;
import com.daymeijroos.iacspring.Product.Product;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    private final LineItemMapper lineItemMapper;
    private final ShippingDetailsMapper shippingDetailsMapper;

    @Autowired
    public OrderMapper(LineItemMapper lineItemMapper, ShippingDetailsMapper shippingDetailsMapper) {
        this.lineItemMapper = lineItemMapper;
        this.shippingDetailsMapper = shippingDetailsMapper;
    }



    public Order fromDTOToEntity(@Nonnull OrderDTO orderDTO) throws ResourceNotFoundException {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setUserId((orderDTO.getUserId()));
        List<LineItem> lineItems = new ArrayList<>();
        for (LineItemDTO lineItemDTO : orderDTO.getLineItems()) {
            lineItems.add(lineItemMapper.fromDTOToEntity(lineItemDTO));
        }
        order.setLineItems(lineItems);
        return order;
    }

    public Order fromDTOToEntityIncludeDetails(@Nonnull OrderDTO orderDTO) throws ResourceNotFoundException {
        Order order = fromDTOToEntity(orderDTO);
        order.setPaymentStatus(orderDTO.getPaymentStatus());
        order.setShippingDetails(shippingDetailsMapper.fromDTOToEntity(orderDTO.getShippingDetails()));
        order.setShippingStatus(orderDTO.getShippingStatus());
        return order;
    }

    public OrderDTO fromEntityToDTO(@Nonnull Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUserId());
        orderDTO.setLineItems(order.getLineItems().stream().map(lineItemMapper::fromEntityToDTO).collect(Collectors.toList()));
        orderDTO.setPaymentStatus(order.getPaymentStatus());
        orderDTO.setShippingDetails(shippingDetailsMapper.fromEntityToDTO(order.getShippingDetails()));
        orderDTO.setShippingStatus(order.getShippingStatus());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setDateCreated(order.getDateCreated());
        orderDTO.setDateModified(order.getDateModified());
        return orderDTO;
    }
}