package com.daymeijroos.iacspring.mapper;

import com.daymeijroos.iacspring.dao.ProductDAO;
import com.daymeijroos.iacspring.dao.UserDAO;
import com.daymeijroos.iacspring.dto.GetOrderDTO;
import com.daymeijroos.iacspring.dto.PlaceOrderDTO;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.model.Order;
import com.daymeijroos.iacspring.model.Product;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public ProductMapper productMapper;
    //public ShippingDetailsMapper shippingDetailsMapper;
    public UserDAO userDAO;
    ProductDAO productDAO;

    @Autowired
    public OrderMapper(ProductMapper productMapper, UserDAO userDAO) {
        this.productMapper = productMapper;
        this.userDAO = userDAO;
    }

    public Order fromDTOToEntity(@Nonnull PlaceOrderDTO placeOrderDTO) throws ResourceNotFoundException {
        Order order = new Order();
        order.setId(placeOrderDTO.getId());
        order.setUser(userDAO.getById(placeOrderDTO.getUserId()));
        List<Product> products = new ArrayList<>();
        for (String productId : placeOrderDTO.getProductIds()) {
            Product product = productDAO.getById(productId);
        }
        order.setProducts(products);

        return order;
    }

    public GetOrderDTO fromEntityToDTO(@Nonnull Order order) {
        GetOrderDTO getOrderDTO = new GetOrderDTO();
        getOrderDTO.setId(order.getId());
        getOrderDTO.setUserId(order.getUser().getId());
        getOrderDTO.setProducts(order.getProducts().stream().map(productMapper::fromEntityToDTO).collect(Collectors.toList()));
        getOrderDTO.setPaymentStatus(order.getPaymentStatus());
        //getOrderDTO.setShippingDetails(shippingDetailsMapper.fromEntityToDTO(order.getShippingDetails()));
        getOrderDTO.setShippingStatus(order.getShippingStatus());
        getOrderDTO.setTotalPrice(order.getTotalPrice());
        return getOrderDTO;
    }
}