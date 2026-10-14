package com.example.demo.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    //Kayıt bulunamadı --> 404
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException ex){
        log.warn("Kayıt bulunamadı: {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //Validation hatası --> 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidation(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        for (FieldError error: ex.getBindingResult().getFieldErrors()){
            errors.put(error.getField(),error.getDefaultMessage());
        }
        log.warn("Validasyon Hatası: {}",errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    //Kategori adı zaten mevcut --> 409
    @ExceptionHandler(DuplicateCategoryException.class)
    public ResponseEntity<String> handleDuplicateCategory(DuplicateCategoryException ex){
        log.atWarn()
                .addKeyValue("eventCode", "CATEGORY_DUPLICATE")
                .addKeyValue("categoryName", ex.getCategoryName())
                .log("Kategori çakışması");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }


    //Veritabanı kısıtlama ihlali --> 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrity(DataIntegrityViolationException ex){
        log.warn("Veri bütünlüğü ihlali: {}",ex.getMostSpecificCause().getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Veri bütünlüğü kısıtı ihlal edildi.");
    }

    //Beklenmedik durumlar --> 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception ex){
        log.error("Beklenmeyen hata",ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Beklenmeyen bir hata oluştu.");
    }
}