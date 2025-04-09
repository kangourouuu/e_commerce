package com.example.auth_service.dao;

import com.example.auth_service.entity.Orders;
import com.example.auth_service.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersDAOImpl implements OrdersDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Orders> findAll() {
        TypedQuery<Orders> query = em.createQuery("from Orders", Orders.class);
        return query.getResultList();
    }

    @Override
    public Orders save(Orders orders) {
        return em.merge(orders);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Orders orders = em.find(Orders.class, id);
        em.remove(orders);
    }


    @Autowired
    public OrdersDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Orders findById(long id) {
        return em.find(Orders.class, id);
    }
}
