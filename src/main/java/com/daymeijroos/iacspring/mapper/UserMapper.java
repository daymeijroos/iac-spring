package com.daymeijroos.iacspring.mapper;

import com.daymeijroos.iacspring.dao.UserDAO;
import com.daymeijroos.iacspring.dto.UserDTO;
import com.daymeijroos.iacspring.model.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserMapper implements Mapper<User, UserDTO> {
    private final UserDAO userDAO;

    @Override
    public User fromDTOToEntity(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO fromEntityToDTO(User user) {
        return null;
    }

    @Override
    public User fromIdToEntity(String id) {
        return null;
    }
}
