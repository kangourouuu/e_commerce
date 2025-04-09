package com.example.auth_service.service;


import com.example.auth_service.dao.UserDAO;
import com.example.auth_service.entity.Authority;
import com.example.auth_service.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final AuthorityServiceImpl authorityServiceImpl;
    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, AuthorityServiceImpl authorityServiceImpl) {
        this.userDAO = userDAO;
        this.authorityServiceImpl = authorityServiceImpl;
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Transactional
    @Override
    public User save(User user) {
        return userDAO.save(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userDAO.deleteById(id);
    }
}