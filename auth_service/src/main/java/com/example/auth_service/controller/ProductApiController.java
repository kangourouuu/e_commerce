package com.example.auth_service.controller;


import com.example.auth_service.entity.Product;
import com.example.auth_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prapi")
public class ProductApiController {
    private final ProductService productService;

    @Autowired
    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById (@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        if (product != null) {
            return ResponseEntity.ok(product);  // Trả về sản phẩm nếu tìm thấy
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Trả về 404 nếu không tìm thấy sản phẩm
        }
    }
}
