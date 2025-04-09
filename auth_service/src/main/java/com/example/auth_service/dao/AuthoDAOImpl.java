package com.example.auth_service.dao;


import com.example.auth_service.entity.Authority;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthoDAOImpl implements AuthorityDAO {

    private EntityManager em;

    @Autowired
    public AuthoDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Authority> findAll() {
        TypedQuery<Authority> query = em.createQuery("from Authority", Authority.class);
        return query.getResultList();
    }

    @Override
    public Authority findById(Long id) {
        return em.find(Authority.class, id);
    }

    @Override
    @Transactional
    public Authority save(Authority authority) {
        return em.merge(authority);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Authority authority = em.find(Authority.class, id);
        if (authority != null) {
            em.remove(authority);
        }
    }

    @Override
    public Authority findByName(String name) {
        TypedQuery<Authority> query = em.createQuery("from Authority a where a.name = :name", Authority.class);
        query.setParameter("name", name);
        List<Authority> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }
}