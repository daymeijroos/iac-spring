package com.daymeijroos.iacspring.Category;

import com.daymeijroos.iacspring.Product.ProductDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryDTO {
    private String id;

    @NotBlank(message="Name cannot be empty.")
    private String name;

    private String description;

    private List<ProductDTO> products = new ArrayList<>();
}
