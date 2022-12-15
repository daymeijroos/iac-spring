package com.daymeijroos.iacspring.dto;

import com.daymeijroos.iacspring.enums.PaymentStatus;
import com.daymeijroos.iacspring.enums.ShippingStatus;
import com.daymeijroos.iacspring.model.Product;
import com.daymeijroos.iacspring.model.ShippingDetails;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class GetOrderDTO {
    private String id;

    @NotBlank(message = "User ID cannot be empty.")
    private String userId;

    @NotEmpty(message = "Product IDs list cannot be empty")
    private List<Product> products;

    @NotNull(message = "Payment status cannot be empty.")
    PaymentStatus paymentStatus;

    @NotNull(message = "Shipping details cannot be empty.")
    ShippingDetails shippingDetails;

    @NotNull(message = "Shipping status cannot be empty.")
    ShippingStatus shippingStatus;

    @NotNull(message = "Total price cannot be empty.")
    Float TotalPrice;
}
