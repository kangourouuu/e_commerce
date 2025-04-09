package com.example.auth_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // Ví dụ: ROLE_ADMIN, ROLE_USER

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Tên quyền không được để trống") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Tên quyền không được để trống")String name) {
        this.name = name;
    }
}