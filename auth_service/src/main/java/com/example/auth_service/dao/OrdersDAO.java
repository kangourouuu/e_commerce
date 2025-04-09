package com.example.auth_service.dao;

import com.example.auth_service.entity.Orders;

import java.util.List;

public interface OrdersDAO {
    List<Orders> findAll();
    Orders findById(long id);
    Orders save (Orders orders);
    void deleteById(long id);
}
