package com.example.auth_service.service;

import com.example.auth_service.entity.Orders;

import java.util.List;

public interface OrdersService {
    List<Orders> findAll();
    Orders findById(long id);
    Orders save(Orders orders);
    void deleteById(Long id);
}
