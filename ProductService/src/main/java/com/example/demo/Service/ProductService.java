package com.example.demo.Service;

import com.example.demo.Model.CategoryEntity;
import com.example.demo.Model.ProductEntity;
import com.example.demo.Model.ProductRequest;
import com.example.demo.Model.ProductResponse;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductResponse> getAllProducts(){
        return productRepository.findAll()//repositorydeki hepsi gelir
                .stream()
                .map(this::toResponse)//her biri ProductResponse'a çevrilir
                .collect(Collectors.toList());//akıştaki elemanlar yeni bir listeye toplanır
    }

    public ProductResponse getProductById(Long id){
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Böyle bir ürün yok."));
        return toResponse(product);
    }

    public ProductResponse createProduct(ProductRequest request, Long categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NoSuchElementException("Böyle bir kategori yok."));

        ProductEntity product = new ProductEntity();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setCategoryEntity(category);

        ProductEntity saved = productRepository.save(product);
        return toResponse(saved);
    }

    public void deleteProduct(Long id){
        if (!productRepository.existsById(id)){
            throw new NoSuchElementException("Böyle bir ürün yok");
        }
        productRepository.deleteById(id);
    }

    public ProductResponse updateProduct(Long id, ProductRequest request){
        ProductEntity existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Böyle bir ürün yok."));

        existingProduct.setName(request.getName());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setStockQuantity(request.getStockQuantity());

        ProductEntity saved = productRepository.save(existingProduct);
        return toResponse(saved);
    }

    // ---- Entity → Response çevirici (tekrar etmemek için tek yerde) ----
    private ProductResponse toResponse(ProductEntity product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setStockQuantity(product.getStockQuantity());
        return response;
    }
}