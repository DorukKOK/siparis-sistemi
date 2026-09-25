package com.example.demo.Service;

import com.example.demo.Model.CategoryEntity;
import com.example.demo.Model.CategoryRequest;
import com.example.demo.Model.CategoryResponse;
import com.example.demo.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryResponse> getAllCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CategoryResponse getCategoryById(Long id){
        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Böyle bir kategori yok"));
        return toResponse(category);
    }

    public CategoryResponse createCategory(CategoryRequest request){
        CategoryEntity category = new CategoryEntity();
        category.setName(request.getName());

        CategoryEntity saved = categoryRepository.save(category);
        return toResponse(saved);
    }

    public CategoryResponse updateCategory(Long id, CategoryRequest request){
        CategoryEntity existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Böyle bir kategori yok"));

        existingCategory.setName(request.getName());

        CategoryEntity saved = categoryRepository.save(existingCategory);
        return toResponse(saved);
    }

    public void deleteCategory(Long id){
        if (!categoryRepository.existsById(id)){
            throw new NoSuchElementException("Böyle bir kategori yok.");
        }
        categoryRepository.deleteById(id);
    }

    // ---- Entity → Response çevirici ----
    private CategoryResponse toResponse(CategoryEntity category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        return response;
    }
}