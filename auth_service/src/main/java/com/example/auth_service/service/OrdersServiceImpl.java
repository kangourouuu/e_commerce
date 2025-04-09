package com.example.auth_service.service;

import com.example.auth_service.dao.OrdersDAO;
import com.example.auth_service.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService{

    private OrdersDAO ordersDAO;

    @Autowired
    public OrdersServiceImpl(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    @Override
    public List<Orders> findAll() {
        return ordersDAO.findAll();
    }

    @Override
    public Orders findById(long id) {
        return ordersDAO.findById(id);
    }

    @Override
    @Transactional
    public Orders save(Orders orders) {
        return ordersDAO.save(orders);
    }

    @Override
    public void deleteById(Long id) {
        ordersDAO.deleteById(id);
    }
}
