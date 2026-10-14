package com.example.demo.api.exception;

public class DuplicateCategoryException extends RuntimeException {
    private final String CategoryName;

    public DuplicateCategoryException(String categoryName) {
        super("Bu isimde bir kategori zaten var: " + categoryName);
        this.CategoryName = categoryName;
    }

    public String getCategoryName() {
        return CategoryName;
    }
}