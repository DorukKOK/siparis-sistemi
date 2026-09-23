package com.example.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    @NotBlank(message = "ad soyad boş olamaz")
    private String fullName;

    @Column(name ="email")
    @NotBlank(message = "mail boş olamaz")
    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


}
