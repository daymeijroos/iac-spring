package com.daymeijroos.iacspring.mapper;

import com.daymeijroos.iacspring.dao.UserDAO;
import com.daymeijroos.iacspring.dto.ShippingDetailsDTO;
import com.daymeijroos.iacspring.exception.ResourceNotFoundException;

import com.daymeijroos.iacspring.model.ShippingDetails;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShippingDetailsMapper {
    public UserDAO userDAO;

    @Autowired
    public ShippingDetailsMapper(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public ShippingDetails fromDTOToEntity(@Nonnull ShippingDetailsDTO shippingDetailsDTO) throws ResourceNotFoundException {
        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setId(shippingDetailsDTO.getId());
        shippingDetails.setUser(userDAO.getById(shippingDetailsDTO.getUserId()));
        shippingDetails.setFirstName(shippingDetailsDTO.getFirstName());
        shippingDetails.setLastName(shippingDetailsDTO.getLastName());
        shippingDetails.setPhone(shippingDetailsDTO.getPhone());
        shippingDetails.setCountry(shippingDetailsDTO.getCountry());
        shippingDetails.setCity(shippingDetailsDTO.getCity());
        shippingDetails.setAddress(shippingDetailsDTO.getAddress());
        shippingDetails.setPostalCode(shippingDetailsDTO.getPostalCode());
        return shippingDetails;
    }

    public ShippingDetailsDTO fromEntityToDTO(@Nonnull ShippingDetails shippingDetails) {
        ShippingDetailsDTO shippingDetailsDTO = new ShippingDetailsDTO();
        shippingDetailsDTO.setId(shippingDetails.getId());
        shippingDetailsDTO.setUserId(shippingDetails.getUser().getId());
        shippingDetailsDTO.setFirstName(shippingDetails.getFirstName());
        shippingDetailsDTO.setLastName(shippingDetails.getLastName());
        shippingDetailsDTO.setPhone(shippingDetails.getPhone());
        shippingDetailsDTO.setCountry(shippingDetails.getCountry());
        shippingDetailsDTO.setCity(shippingDetails.getCity());
        shippingDetailsDTO.setAddress(shippingDetails.getAddress());
        shippingDetailsDTO.setPostalCode(shippingDetails.getPostalCode());
        return shippingDetailsDTO;
    }
}