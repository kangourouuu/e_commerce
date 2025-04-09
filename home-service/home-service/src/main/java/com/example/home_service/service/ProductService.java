package com.example.home_service.service;

import ch.qos.logback.classic.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import com.example.home_service.entity.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class ProductService {
    Logger logger;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${admin-service.base-url}")
    private String adminServiceBaseUrl;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

//    public ProductService(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder;
//    }

    public List<Product> getProductsFromAdminService() {
        try {
            String cacheKey = "products"; // Cache key cho danh sách sản phẩm

            // Kiểm tra cache Redis trước
            List<Product> cachedProducts = (List<Product>) redisTemplate.opsForValue().get(cacheKey);

            if (cachedProducts != null) {
                // Nếu có, trả về danh sách từ cache
                return cachedProducts;
            }

            // Nếu không có trong cache, gọi từ admin-service
            String fullUrl = adminServiceBaseUrl + "/products/list-product";
            System.out.println("Calling admin service at: " + fullUrl);

            ResponseEntity<List<Product>> response = restTemplate.exchange(
                    fullUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Product>>() {
                    }
            );

            // Lưu vào Redis để sử dụng lần sau
            redisTemplate.opsForValue().set(cacheKey, response.getBody());

            return response.getBody();
        } catch (Exception e) {
            System.err.println("Error calling admin service: " + e.getMessage());
            e.printStackTrace();
            return List.of(); // Trả về danh sách rỗng để tránh lỗi
        }
    }

    public Product getProductById(Long id) {
        try {
            String fullUrl = adminServiceBaseUrl + "/prapi/product/" + id;
            System.out.println("Calling admin service at: " + fullUrl);

            // Sử dụng RestTemplate.exchange để ánh xạ trực tiếp phản hồi thành đối tượng Product
            ResponseEntity<Product> response = restTemplate.exchange(
                    fullUrl,
                    HttpMethod.GET,
                    null,
                    Product.class
            );

            // Kiểm tra mã trạng thái và nội dung phản hồi
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();  // Trả về sản phẩm nếu thành công
            } else {
                // Ghi log nếu không phải 200 OK (Có thể là lỗi 404 hoặc 500)
                System.out.println("Received non-OK status code: " + response.getStatusCode());
            }

        } catch (Exception e) {
            System.err.println("Error fetching product by ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}

