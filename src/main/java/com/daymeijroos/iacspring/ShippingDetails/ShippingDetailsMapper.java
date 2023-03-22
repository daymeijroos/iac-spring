package com.daymeijroos.iacspring.ShippingDetails;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class ShippingDetailsMapper {
    public ShippingDetails fromDTOToEntity(@Nonnull ShippingDetailsDTO shippingDetailsDTO) {
        ShippingDetails shippingDetails = new ShippingDetails();
        shippingDetails.setUserId(shippingDetailsDTO.getUserId());
        shippingDetails.setFirstName(shippingDetailsDTO.getFirstName());
        shippingDetails.setLastName(shippingDetailsDTO.getLastName());
        shippingDetails.setPhone(shippingDetailsDTO.getPhone());
        shippingDetails.setEmail(shippingDetailsDTO.getEmail());
        shippingDetails.setCountry(shippingDetailsDTO.getCountry());
        shippingDetails.setCity(shippingDetailsDTO.getCity());
        shippingDetails.setAddress(shippingDetailsDTO.getAddress());
        shippingDetails.setPostalCode(shippingDetailsDTO.getPostalCode());
        return shippingDetails;
    }

    public ShippingDetailsDTO fromEntityToDTO(@Nonnull ShippingDetails shippingDetails) {
        ShippingDetailsDTO shippingDetailsDTO = new ShippingDetailsDTO();
        shippingDetailsDTO.setFirstName(shippingDetails.getFirstName());
        shippingDetailsDTO.setLastName(shippingDetails.getLastName());
        shippingDetailsDTO.setPhone(shippingDetails.getPhone());
        shippingDetailsDTO.setEmail(shippingDetails.getEmail());
        shippingDetailsDTO.setCountry(shippingDetails.getCountry());
        shippingDetailsDTO.setCity(shippingDetails.getCity());
        shippingDetailsDTO.setAddress(shippingDetails.getAddress());
        shippingDetailsDTO.setPostalCode(shippingDetails.getPostalCode());
        return shippingDetailsDTO;
    }
}