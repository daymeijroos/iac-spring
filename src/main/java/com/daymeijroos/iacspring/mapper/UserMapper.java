package com.daymeijroos.iacspring.mapper;

import com.daymeijroos.iacspring.dto.UserDTO;
import com.daymeijroos.iacspring.model.User;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDTO> {
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
        userDTO.setShippingDetails(user.getShippingDetails());
        //userDTO.setOrders();
        return userDTO;
    }
}
