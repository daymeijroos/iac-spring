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

    public Order fromDTOToEntity(@Nonnull PlaceOrderDTO placeOrderDTO) {
        Order order = new Order();
        order.setId(placeOrderDTO.getId());
        order.setUserId((placeOrderDTO.getUserId()));
        List<Product> products = new ArrayList<>();
        for (String productId : placeOrderDTO.getProductIds()) {
            try {
                products.add(productDAO.getById(productId));
            } catch (ResourceNotFoundException ignored) {};
        }
        order.setProducts(products);

        return order;
    }

    public GetOrderDTO fromEntityToDTO(@Nonnull Order order) {
        GetOrderDTO getOrderDTO = new GetOrderDTO();
        getOrderDTO.setId(order.getId());
        getOrderDTO.setUserId(order.getUserId());
        getOrderDTO.setProducts(order.getProducts().stream().map(productMapper::fromEntityToDTO).collect(Collectors.toList()));
        getOrderDTO.setPaymentStatus(order.getPaymentStatus());
        getOrderDTO.setShippingDetails(shippingDetailsMapper.fromEntityToDTO(order.getShippingDetails()));
        getOrderDTO.setShippingStatus(order.getShippingStatus());
        getOrderDTO.setTotalPrice(order.getTotalPrice());
        return getOrderDTO;
    }
}