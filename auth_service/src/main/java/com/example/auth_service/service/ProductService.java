package com.example.auth_service.service;

import com.example.auth_service.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product); // Changed parameter name to 'product'
    void deleteById(Long id);
}