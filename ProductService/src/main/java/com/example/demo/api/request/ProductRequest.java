package com.example.demo.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest {
    //Ürün oluşturuken veya güncellerken kullanıcının vermesi gereken bilgiler.

    @NotBlank(message = "isim boş olamaz.")
    private String name;

    @Positive(message = "tutar 0 dan büyük olmalıdır.")
    private BigDecimal price;

    @PositiveOrZero(message = "stok miktarı negatif olamaz")
    private Integer stockQuantity;
}

