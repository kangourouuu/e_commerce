package com.example.auth_service.controller;


import com.example.auth_service.entity.Product;
import com.example.auth_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@CrossOrigin(origins = "http://localhost:8081")
@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/list-product")
    public String listProducts(Model model) {
        // Lấy danh sách sản phẩm từ Redis (nếu đã lưu trữ trước đó)
        List<Product> products = (List<Product>) redisTemplate.opsForValue().get("products");

        if (products == null) {
            // Nếu chưa có, lấy từ cơ sở dữ liệu và lưu vào Redis
            products = productService.findAll();
            redisTemplate.opsForValue().set("products", products);
        }

        model.addAttribute("products", products);
        return "admin/list-product";
    }

    @GetMapping("/product-form-insert")
    public String formInsertProduct(Model model) {
        Product product = new Product();
        redisTemplate.delete("products");
        model.addAttribute("product", product);
        return "admin/product-form-insert"; // Assuming your insert form template is in the admin folder
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/product-form-insert"; // Assuming your insert form template is in the admin folder
        }
        productService.save(product);
        return "redirect:/products/list-product";
    }

    @GetMapping("/product-form-update")
    public String formUpdateProduct(@RequestParam("id") Long id, Model model) {
        Product product = productService.findById(id);
        redisTemplate.delete("products");
        model.addAttribute("product", product);
        return "admin/product-form-update"; // Assuming your update form template is in the admin folder
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("id") Long id) {
        productService.deleteById(id);
        redisTemplate.delete("products");
        return "redirect:/products/list-product";
    }
}