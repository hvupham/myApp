package org.project.myapp.Services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.project.myapp.Repositoris.CategoryRepositoriy;
import org.project.myapp.dtos.CategoryDTO;
import org.project.myapp.models.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CategoryService implements ICategoryService {

    private final CategoryRepositoriy categoryRepository;

    @Override

    public Category createCategory(CategoryDTO categoryDTO) {
        Category newCategory = Category
                .builder()
                .name(categoryDTO.getName())
                .build();
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("category not found")) ;

    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(long categoryId, CategoryDTO categoryDTO) {
        Category existingCategory = getCategoryById(categoryId);
        existingCategory.setName(categoryDTO.getName());
        categoryRepository.save(existingCategory);
        return existingCategory;
    }

    @Override
    public void deleteCategory(long id) {
        //xoa xong  mat luon
        categoryRepository.deleteById(id);
    }
}
