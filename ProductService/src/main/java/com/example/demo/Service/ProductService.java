package com.example.demo.Service;

import com.example.demo.Model.CategoryEntity;
import com.example.demo.Model.ProductEntity;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    public List<ProductEntity> getAllProducts(){
        return productRepository.findAll();
    }
    public ProductEntity getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Böyle bir ürün yok."));
    }

    public ProductEntity createProduct(ProductEntity product, Long categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Böyle bir kategori yok."));

        product.setCategoryEntity(category);

        return productRepository.save(product);
    }
    public void deleteProduct(Long id){
        if (!productRepository.existsById(id)){
            throw new RuntimeException("Böyle bir ürün yok");
        }
        productRepository.deleteById(id);
    }
    public ProductEntity updateProduct(Long id , ProductEntity updatedData){
        ProductEntity existingProduct = productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Böyle bir ürün yok."));
        existingProduct.setName(updatedData.getName());
        existingProduct.setPrice(updatedData.getPrice());
        existingProduct.setStockQuantity(updatedData.getStockQuantity());

        return productRepository.save(existingProduct);
    }
}
