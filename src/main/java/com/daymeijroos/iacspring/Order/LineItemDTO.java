package com.daymeijroos.iacspring.Order;

import com.daymeijroos.iacspring.Product.ProductDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LineItemDTO {
    @NotBlank(message = "Quantity cannot be empty")
    int quantity;

    ProductDTO product;
}
