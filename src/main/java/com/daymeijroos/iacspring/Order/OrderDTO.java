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

    private String userId;

    @NotEmpty(message = "Line items cannot be empty")
    private List<LineItemDTO> lineItems;

    @NotNull(message = "Payment method cannot be empty")
    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    @NotNull(message = "Shipping details cannot be empty")
    private ShippingDetailsDTO shippingDetails;

    private Date dateCreated;

    private Date dateModified;

    private ShippingStatus shippingStatus;

    private Float TotalPrice;
}
