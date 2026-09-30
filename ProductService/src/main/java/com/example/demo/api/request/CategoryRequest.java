package com.example.demo.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
public class CategoryRequest {
    @NotBlank(message = "isim boş olamaz.")
    private String name;
}
