package com.daymeijroos.iacspring.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class ProductDTO {
    private String id;
    @NotNull(message="Name cannot be empty.")
    private String name;
    @NotNull(message="Description cannot be empty.")
    private String description;
    @NotNull(message="Price cannot be empty.")
    private Float price;
    @NotNull(message="Image cannot be empty.")
    private String imageURL;
}
