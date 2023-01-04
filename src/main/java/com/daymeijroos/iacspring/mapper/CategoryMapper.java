package com.daymeijroos.iacspring.mapper;

import com.daymeijroos.iacspring.dto.CategoryDTO;
import com.daymeijroos.iacspring.model.Category;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryMapper implements Mapper<Category, CategoryDTO> {
    private final ProductMapper productMapper;

    @Autowired
    public CategoryMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public Category fromDTOToEntity(@Nonnull CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setProducts(categoryDTO.getProducts().stream().map(productMapper::fromDTOToEntity).collect(Collectors.toList()));
        return category;
    }

    @Override
    public CategoryDTO fromEntityToDTO(@Nonnull Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setProducts(category.getProducts().stream().map(productMapper::fromEntityToDTO).collect(Collectors.toList()));
        return categoryDTO;
    }
}