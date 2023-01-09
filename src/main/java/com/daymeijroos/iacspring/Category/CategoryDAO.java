package com.daymeijroos.iacspring.Category;

        import com.daymeijroos.iacspring.shared.DAO;
        import com.daymeijroos.iacspring.exception.ResourceNotFoundException;
        import com.daymeijroos.iacspring.repository.CategoryRepository;
        import lombok.AllArgsConstructor;
        import org.springframework.stereotype.Component;

        import java.util.List;
        import java.util.Optional;

@AllArgsConstructor
@Component
public class CategoryDAO implements DAO<Category> {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category getById(String id) throws ResourceNotFoundException {
        Optional<Category> result = categoryRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new ResourceNotFoundException("Category", id);
        }
    }

    @Override
    public Category saveToDatabase(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category update(String id, Category categoryRequest) throws ResourceNotFoundException {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", id));
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public void delete(String id) throws ResourceNotFoundException {
        categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", id));
        this.categoryRepository.deleteById(id);
    }
}
