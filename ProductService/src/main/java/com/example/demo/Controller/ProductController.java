package com.example.demo.Controller;
import com.example.demo.Model.ProductEntity;
import com.example.demo.Model.ProductRequest;
import com.example.demo.Model.ProductResponse;
import com.example.demo.Service.CategoryService;
import com.example.demo.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping()
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }



    @PostMapping("/{categoryId}")
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest request, @PathVariable Long categoryId){
        return productService.createProduct(request, categoryId);
    }
    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Long id , @RequestBody ProductRequest product){
        return productService.updateProduct(id, product);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
