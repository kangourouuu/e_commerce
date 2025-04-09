package com.example.auth_service.dao;



import com.example.auth_service.entity.Authority;

import java.util.List;

public interface AuthorityDAO {
    List<Authority> findAll();
    Authority findById(Long id);
    Authority save(Authority authority);
    void deleteById(Long id);
    Authority findByName(String name);
}