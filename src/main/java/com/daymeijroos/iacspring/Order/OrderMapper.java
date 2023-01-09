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
    private final ProductMapper productMapper;
    private final ShippingDetailsMapper shippingDetailsMapper;
    private final ProductDAO productDAO;

    @Autowired
    public OrderMapper(ProductDAO productDAO, ProductMapper productMapper, ShippingDetailsMapper shippingDetailsMapper) {
        this.productDAO = productDAO;
        this.productMapper = productMapper;
        this.shippingDetailsMapper = shippingDetailsMapper;
    }

    public Order fromDTOToEntity(@Nonnull OrderDTOIn orderDTOIn) {
        Order order = new Order();
        order.setId(orderDTOIn.getId());
        order.setUserId((orderDTOIn.getUserId()));
        List<Product> products = new ArrayList<>();
        for (String productId : orderDTOIn.getProductIds()) {
            try {
                products.add(productDAO.getById(productId));
            } catch (ResourceNotFoundException ignored) {};
        }
        order.setProducts(products);

        return order;
    }

    public OrderDTOOut fromEntityToDTO(@Nonnull Order order) {
        OrderDTOOut orderDTOOut = new OrderDTOOut();
        orderDTOOut.setId(order.getId());
        orderDTOOut.setUserId(order.getUserId());
        orderDTOOut.setProducts(order.getProducts().stream().map(productMapper::fromEntityToDTO).collect(Collectors.toList()));
        orderDTOOut.setPaymentStatus(order.getPaymentStatus());
        orderDTOOut.setShippingDetails(shippingDetailsMapper.fromEntityToDTO(order.getShippingDetails()));
        orderDTOOut.setShippingStatus(order.getShippingStatus());
        orderDTOOut.setTotalPrice(order.getTotalPrice());
        return orderDTOOut;
    }
}