package com.example.auth_service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;
    
    @NotBlank(message = "Tên sản phẩm không được để trống.")
    @Column(name = "name", nullable = false, length = 255)
    @JsonProperty("name")
    private String name;
    
    @NotNull(message = "Giá sản phẩm không được để trống.")
    @Min(value = 0, message = "Giá sản phẩm phải lớn hơn hoặc bằng 0.")
    @Column(name = "price", nullable = false)
    @JsonProperty("price")
    private Double price;

    @JsonProperty("description")
    private String description;

    @JsonProperty("imageUrl")
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Tên sản phẩm không được để trống.") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Tên sản phẩm không được để trống.") String name) {
        this.name = name;
    }

    public @NotNull(message = "Giá sản phẩm không được để trống.") @Min(value = 0, message = "Giá sản phẩm phải lớn hơn hoặc bằng 0.") Double getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = "Giá sản phẩm không được để trống.") @Min(value = 0, message = "Giá sản phẩm phải lớn hơn hoặc bằng 0.") Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
