package com.example.auth_service.service;


import com.example.auth_service.entity.Authority;

import java.util.List;

public interface AuthorityService {
    List<Authority> findAll();
    Authority findById(Long id);
    Authority findByName(String name);
    Authority save(Authority authority);
    void deleteById(Long id);
}