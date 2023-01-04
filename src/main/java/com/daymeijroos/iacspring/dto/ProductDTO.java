package com.daymeijroos.iacspring.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class ProductDTO {
    private String id;

    @NotBlank(message="Name cannot be empty.")
    private String name;

    @NotBlank(message="Description cannot be empty.")
    private String description;

    @NotBlank(message="Price cannot be empty.")
    private Float price;

    @NotBlank(message="Image cannot be empty.")
    private String imageUrl;

    @NotBlank(message="Category_name Cannot be empty.")
    private String categoryId;

    private String categoryName;
}
