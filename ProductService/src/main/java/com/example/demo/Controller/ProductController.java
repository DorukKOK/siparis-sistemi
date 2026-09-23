package com.example.demo.Controller;
import com.example.demo.Model.ProductEntity;
import com.example.demo.Service.CategoryService;
import com.example.demo.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }
    @GetMapping()
    public List<ProductEntity> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public ProductEntity getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @PostMapping("/{categoryId}")
    public ProductEntity createProduct(@Valid @RequestBody ProductEntity product, @PathVariable Long categoryId){
        return productService.createProduct(product, categoryId);
    }
    @PutMapping("/{id}")
    public ProductEntity updateProduct(@PathVariable Long id , @RequestBody ProductEntity product){
        return productService.updateProduct(id, product);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
