package com.example.auth_service.controller;


import com.example.auth_service.entity.Customer;
import com.example.auth_service.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/list-customer")
    public String list(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "admin/list-customer";
    }

    @GetMapping("/customer-form-insert")
    public String formInsert(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "admin/customer-form-insert";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/customer-form-insert";
        }
        customerService.save(customer);
        return "redirect:/customers/list-customer";
    }

    @GetMapping("/customer/{id}")
    public Customer customerById (@PathVariable Long id) {
        return customerService.findById(id);
    }

    @GetMapping("/customer-form-update")
    public String formUpdate(@RequestParam("id") Long id, Model model) {
        Customer customer = customerService.findById(id);
        redisTemplate.delete("customers");
        model.addAttribute("customer", customer);
        return "admin/customer-form-update";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        customerService.deleteById(id);
        redisTemplate.delete("customers");
        return "redirect:/customers/list-customer";
    }

}
