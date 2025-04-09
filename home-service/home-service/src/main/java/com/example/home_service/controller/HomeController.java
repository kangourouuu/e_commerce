package com.example.home_service.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.home_service.entity.Product;
import com.example.home_service.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;
    
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String PRODUCTS_KEY = "products";

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Hello World");
        return "home"; // Trả về file home.html trong thư mục templates
    }


    @GetMapping("/catalog")
    public String products(Model model) {
        // Kiểm tra Redis trước
        List<Product> products = (List<Product>) redisTemplate.opsForValue().get(PRODUCTS_KEY);

        // Nếu không có sản phẩm trong Redis, lấy từ ProductService và lưu vào Redis
        if (products == null || products.isEmpty()) {
            products = productService.getProductsFromAdminService();  // Lấy danh sách sản phẩm từ Admin Service
            redisTemplate.opsForValue().set(PRODUCTS_KEY, products);  // Lưu danh sách sản phẩm vào Redis
        }

        model.addAttribute("products", products);  // Truyền danh sách sản phẩm vào model
        return "list-products";  // Tên template để hiển thị danh sách sản phẩm
    }

    @GetMapping("/catalog/{id}")
    public String getProductById(@PathVariable Long id,Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "error";
        }

        model.addAttribute("product", product);
        return "product-detail";
    }

    @GetMapping("/register")
    public String redirectToRegister(RedirectAttributes redirectAttributes) {
        return "redirect:http://localhost:8080/register";
    }

    @GetMapping("/login")
    public String redirectToLogin(RedirectAttributes redirectAttributes) {
        return "redirect:http://localhost:8080/login";
    }

    @PostMapping("/buy-now/{id}")
    public String buyProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("orderMessage", "Đặt hàng thành công!");

        // Chuyển hướng về trang catalog
        return "redirect:/catalog";
    }

    @GetMapping("/testimonials")
    public String testimonials(){
        return "testimonials";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }
     // Xử lý lỗi 404
    @GetMapping("/error")
    public String handleError() {
        return "error"; // Tạo file error.html trong templates/
    }
}