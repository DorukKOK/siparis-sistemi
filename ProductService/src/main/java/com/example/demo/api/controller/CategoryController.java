package com.example.demo.api.controller;

import com.example.demo.api.request.CategoryRequest;
import com.example.demo.api.response.CategoryResponse;
import com.example.demo.api.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping()
    public List<CategoryResponse> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }
    @PostMapping()
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest category){
        return categoryService.createCategory(category);
    }
    @PutMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable Long id,@Valid @RequestBody CategoryRequest category){
        return categoryService.updateCategory(id,category);
    }

    @DeleteMapping("/{id}")
    public void deletCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
}
