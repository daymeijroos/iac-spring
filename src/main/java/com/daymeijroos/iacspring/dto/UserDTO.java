package com.daymeijroos.iacspring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private String id;

    @NotBlank(message = "E-mail address cannot be empty")
    @Email(message = "This is not a valid E-mail address")
    private String email;

    private ShippingDetailsDTO shippingDetails;

    private List<GetOrderDTO> orders = new ArrayList<>();
}
