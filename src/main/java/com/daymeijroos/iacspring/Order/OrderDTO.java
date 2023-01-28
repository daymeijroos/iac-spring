package com.daymeijroos.iacspring.Order;

import com.daymeijroos.iacspring.ShippingDetails.ShippingDetailsDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class OrderDTO {
    private String id;

    @NotBlank(message = "User ID cannot be empty.")
    private String userId;

    @NotEmpty(message = "Product IDs list cannot be empty")
    private List<LineItemDTO> lineItems;

    private PaymentStatus paymentStatus;

    private ShippingDetailsDTO shippingDetails;

    private Date dateCreated;

    private Date dateModified;

    private ShippingStatus shippingStatus;

    private Float TotalPrice;
}
