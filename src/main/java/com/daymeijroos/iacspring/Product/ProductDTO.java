package com.daymeijroos.iacspring.Product;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class ProductDTO {
    private String id;

    @NotBlank(message="Name cannot be empty.")
    private String name;

    @NotBlank(message="Description cannot be empty.")
    private String description;

    @NotNull(message="Price cannot be null.")
    private Float price;

    @NotBlank(message="Image cannot be empty.")
    private String imageUrl;

    @NotBlank(message="Category id cannot be empty.")
    private String categoryId;

    private String categoryName;

    @NotNull(message="Filter cannot be empty.")
    private ProductFilter filter;
}
