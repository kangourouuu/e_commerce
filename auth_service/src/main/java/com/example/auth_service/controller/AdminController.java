package com.example.auth_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("title", "Admin Dashboard");
        model.addAttribute("pageTitle", "Dashboard");
        return "home"; // Tạo file dashboard.html nếu bạn muốn có trang dashboard riêng
    }
}
