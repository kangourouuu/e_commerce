package com.example.auth_service.controller;


import com.example.auth_service.entity.User;
import com.example.auth_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/system/users")
public class UserController {

    private UserService userService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/system/users/list";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/system/users/add";
    }

    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("user") User user) {
        userService.save(user); // Cần xử lý password encoding ở service
        return "redirect:/system/users/list";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        redisTemplate.delete("user");
        model.addAttribute("user", user);
        return "admin/system/users/edit";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        if (user.getPassword() == null) {
            User existingUser = userService.findById(user.getId());
            user.setPassword(existingUser.getPassword());
        }
        redisTemplate.delete("user");
        userService.save(user); // Cần xử lý password encoding nếu cần
        return "redirect:/system/users/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        redisTemplate.delete("user");
        return "redirect:/system/users/list";
    }
}