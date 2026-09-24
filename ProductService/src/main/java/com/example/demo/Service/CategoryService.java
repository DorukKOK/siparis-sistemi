package com.example.demo.Service;

import com.example.demo.Model.CategoryEntity;
import com.example.demo.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<CategoryEntity> getAllCategories(){
        return categoryRepository.findAll();
    }
    public CategoryEntity getCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Böyle bir kategori yok"));

    }
    public CategoryEntity createCategory(CategoryEntity category){
        return categoryRepository.save(category);
    }
    public CategoryEntity updateCategory(Long id,CategoryEntity category){
        CategoryEntity existingCategory = categoryRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Böyle bir kategori yok"));
        existingCategory.setName(category.getName());
        return categoryRepository.save(existingCategory);
    }
    public void deleteCategory(Long id){
        if (!categoryRepository.existsById(id)){
            throw new NoSuchElementException("Böyle bir kategori yok.");
        }
        categoryRepository.deleteById(id);
    }
}
