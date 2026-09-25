package com.example.demo.Model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest {
    //Ürün oluşturuken veya güncellerken kullanıcının vermesi gereken bilgiler.
    private String name;
    private BigDecimal price;
    private Integer stockQuantity;
}

