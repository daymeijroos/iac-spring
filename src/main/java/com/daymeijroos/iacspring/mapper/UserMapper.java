package com.daymeijroos.iacspring.mapper;

import com.daymeijroos.iacspring.dto.GetOrderDTO;
import com.daymeijroos.iacspring.dto.UserDTO;
import com.daymeijroos.iacspring.model.Order;
import com.daymeijroos.iacspring.model.User;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper implements Mapper<User, UserDTO> {
    //public OrderMapper orderMapper;
    //public ShippingDetailsMapper shippingDetailsMapper;

    //@Autowired
    //public UserMapper(OrderMapper orderMapper, ShippingDetailsMapper shippingDetailsMapper) {
    //    this.orderMapper = orderMapper;
    //    this.shippingDetailsMapper = shippingDetailsMapper;
    //}

    @Override
    public User fromDTOToEntity(@Nonnull UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    @Override
    public UserDTO fromEntityToDTO(@Nonnull User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        //userDTO.setOrders(user.getOrders().stream().map(orderMapper::fromEntityToDTO)
        // .collect(Collectors.toList());
        return userDTO;
    }
}
