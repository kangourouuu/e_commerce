package com.example.auth_service.service;


import com.example.auth_service.dao.AuthorityDAO;
import com.example.auth_service.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private AuthorityDAO authorityDAO;

    @Autowired
    public AuthorityServiceImpl(AuthorityDAO authorityDAO) {
        this.authorityDAO = authorityDAO;
    }

    @Override
    public List<Authority> findAll() {
        return authorityDAO.findAll();
    }

    @Override
    public Authority findById(Long id) {
        return authorityDAO.findById(id);
    }

    @Override
    public Authority findByName(String name) {
        return authorityDAO.findByName(name);
    }

    @Transactional
    @Override
    public Authority save(Authority authority) {
        return authorityDAO.save(authority);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        authorityDAO.deleteById(id);
    }
}