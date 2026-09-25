package com.example.demo.Model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {
    //Ürünü kullanıcıya gösterirken görmesi gereken veya faydalı olabilecek bilgiler
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stockQuantity;
}
