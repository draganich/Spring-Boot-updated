package com.SpringBoot.dao;

import org.springframework.stereotype.Repository;
import com.SpringBoot.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> userList(int num) {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void save(User user) { entityManager.persist(user); }

    @Override
    public User getID(int id) { return entityManager.find(User.class, id); }

    @Override
    public void update(int id, User updatedUser) { entityManager.merge(updatedUser); }

    @Override
    public void delete(int id) { entityManager.remove(entityManager.find(User.class, id)); }
}
