package com.example.auth_service.controller;

import com.example.auth_service.entity.Orders;
import com.example.auth_service.service.OrdersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/orders")
public class OrdersController {

    public OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/list-orders")
    public String listOrders(Model model) {
        List<Orders> orders = ordersService.findAll();
        model.addAttribute("orders", orders);
        return "admin/list-orders";
    };

    @GetMapping("/orders-form-insert")
    public String formInsert(Model model) {
        Orders orders = new Orders();
        model.addAttribute("orders", orders);
        return "admin/orders-form-insert";
    }

    @PostMapping("/save")
    public String saveAuthority(@Valid @ModelAttribute("orders") Orders orders, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/orders-form-insert";
        }
        ordersService.save(orders);
        return "redirect:/admin/orders/list-orders";
    }

    @GetMapping("/orders-form-update")
    public String formUpdateOrders(@RequestParam("id") Long id, Model model) {
        Orders orders = ordersService.findById(id);
        model.addAttribute("orders", orders);
        return "admin/orders-form-update"; // Assuming your update form template is in the admin folder
    }

    @GetMapping("/delete")
    public String deleteOrders(@RequestParam("id") Long id) {
        ordersService.deleteById(id);
        return "redirect:/admin/orders/list-orders";
    }
}
