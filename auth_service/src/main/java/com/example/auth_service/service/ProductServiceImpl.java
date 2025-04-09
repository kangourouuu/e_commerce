package com.example.auth_service.service;


import com.example.auth_service.dao.ProductDAO;
import com.example.auth_service.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService { // Changed class name to 'ProductServiceImpl'

    private ProductDAO productDAO; // Changed variable name to 'productDAO'

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) { // Changed parameter name to 'productDAO'
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productDAO.findById(id);
    }

    @Override
    public Product save(Product product) { // Changed parameter name to 'product'
        return productDAO.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productDAO.deleteById(id);
    }
}
