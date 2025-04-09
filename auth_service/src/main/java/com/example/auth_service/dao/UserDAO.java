package com.example.auth_service.dao;


import com.example.auth_service.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    void deleteById(Long id);
    User findByUsername(String username);
}