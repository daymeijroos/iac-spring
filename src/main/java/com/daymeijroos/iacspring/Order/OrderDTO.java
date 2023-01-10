package com.daymeijroos.iacspring.Order;

import com.daymeijroos.iacspring.ShippingDetails.ShippingDetailsDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private String id;

    @NotBlank(message = "User ID cannot be empty.")
    private String userId;

    @NotEmpty(message = "Product IDs list cannot be empty")
    private List<LineItemDTO> lineItems;

    PaymentStatus paymentStatus;

    ShippingDetailsDTO shippingDetails;

    ShippingStatus shippingStatus;

    Float TotalPrice;
}
