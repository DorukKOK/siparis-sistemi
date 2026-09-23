package com.example.demo.Controller;

import com.example.demo.Model.CategoryEntity;
import com.example.demo.Service.CategoryService;
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
    public List<CategoryEntity> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @GetMapping("/{id}")
    public CategoryEntity getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }
    @PostMapping()
    public CategoryEntity createCategory(@RequestBody CategoryEntity category){
        return categoryService.createCategory(category) ;
    }
    @PutMapping("/{id}")
    public CategoryEntity updateCategory(@PathVariable Long id,@RequestBody CategoryEntity category){
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public void deletCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
}
