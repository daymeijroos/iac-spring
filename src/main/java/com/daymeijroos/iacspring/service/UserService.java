package com.daymeijroos.iacspring.service;

import com.daymeijroos.iacspring.dao.UserDAO;
import com.daymeijroos.iacspring.dto.ProductDTO;
import com.daymeijroos.iacspring.dto.UserDTO;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import com.daymeijroos.iacspring.mapper.UserMapper;
import com.daymeijroos.iacspring.model.Product;
import com.daymeijroos.iacspring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserDAO userDAO;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserDAO userDAO, UserMapper userMapper) {
        this.userDAO = userDAO;
        this.userMapper = userMapper;
    }

    public List<UserDTO> get(String id, String email) throws ResourceNotFoundException {
        if (id != null) {
            List<UserDTO> users = new ArrayList<>();
            User user = userDAO.getById(id);
            users.add(userMapper.fromEntityToDTO(user));
            return users;
        }
        if (email != null) {
            List<UserDTO> users = new ArrayList<>();
            User user = userDAO.getByEmail(email);
            users.add(userMapper.fromEntityToDTO(user));
            return users;
        }
        return userDAO.getAll().stream().map(userMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getByEmail(String email) throws ResourceNotFoundException {
        User user = userDAO.getByEmail(email);
        return userMapper.fromEntityToDTO(user);
    }

    public UserDTO post(UserDTO userDTO) {
        User user = userMapper.fromDTOToEntity(userDTO);
        User savedUser = this.userDAO.saveToDatabase(user);
        return userMapper.fromEntityToDTO(savedUser);
    }

    public UserDTO update(UserDTO userDTO) throws ResourceNotFoundException {
        User user = userMapper.fromDTOToEntity(userDTO);
        User updatedUser = userDAO.update(user.getId(), user);
        return userMapper.fromEntityToDTO(updatedUser);
    }

    public UserDTO patch(UserDTO userDTO) throws ResourceNotFoundException {
        User user = userMapper.fromDTOToEntity(userDTO);
        User updatedUser = userDAO.update(user.getId(), user);
        return userMapper.fromEntityToDTO(updatedUser);
    }

    public void delete(String id) throws ResourceNotFoundException {
        userDAO.delete(id);
    }
}