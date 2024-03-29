package com.daymeijroos.iacspring.Category;

import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryDAO categoryDAO;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryDAO CategoryDAO, CategoryMapper CategoryMapper) {
        this.categoryDAO = CategoryDAO;
        this.categoryMapper = CategoryMapper;
    }

    public List<CategoryDTO> get() {
        return categoryDAO.getAll().stream().map(categoryMapper::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    public List<CategoryDTO> getById(String id) throws ResourceNotFoundException {
        List<CategoryDTO> categories = new ArrayList<>();
        Category category = categoryDAO.getById(id);
        categories.add(categoryMapper.fromEntityToDTO(category));
        return categories;
    }

    public CategoryDTO post(CategoryDTO categoryDTO) {
        Category category = categoryMapper.fromDTOToEntity(categoryDTO);
        Category savedCategory = categoryDAO.saveToDatabase(category);
        return categoryMapper.fromEntityToDTO(savedCategory);
    }

    public CategoryDTO update(CategoryDTO CategoryDTO) throws ResourceNotFoundException {
        Category Category = categoryMapper.fromDTOToEntity(CategoryDTO);
        Category updatedCategory = categoryDAO.update(Category.getId(), Category);
        return categoryMapper.fromEntityToDTO(updatedCategory);
    }

    public CategoryDTO patch(CategoryDTO CategoryDTO) throws ResourceNotFoundException {
        Category category = categoryMapper.fromDTOToEntity(CategoryDTO);
        Category updatedCategory = categoryDAO.update(category.getId(), category);
        return categoryMapper.fromEntityToDTO(updatedCategory);
    }

    public void delete(String id) throws ResourceNotFoundException {
        categoryDAO.delete(id);
    }
}
