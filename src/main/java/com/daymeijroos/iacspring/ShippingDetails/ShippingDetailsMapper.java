package com.daymeijroos.iacspring.ShippingDetails;

import com.daymeijroos.iacspring.exception.ResourceNotFoundException;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class ShippingDetailsMapper {
    public ShippingDetails fromDTOToEntity(@Nonnull ShippingDetailsDTO shippingDetailsDTO) throws ResourceNotFoundException {
        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setId(shippingDetailsDTO.getId());
        shippingDetails.setUserId(shippingDetailsDTO.getUserId());
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
        shippingDetailsDTO.setUserId(shippingDetails.getUserId());
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