package com.example.demo.repository;

import com.example.demo.model.dao.RegularUserDAO;
import com.example.demo.model.dto.UserDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class UserRepository {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD1");

    public Optional<RegularUserDAO> checkCredentials(UserDTO user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<RegularUserDAO> cr = cb.createQuery(RegularUserDAO.class);
        Root<RegularUserDAO> root = cr.from(RegularUserDAO.class);

        Predicate email = cb.equal(root.get("email"), user.getEmail());
        Predicate password = cb.equal(root.get("password"), user.getPassword());

        cr.select(root).where(cb.and(email, password));
        RegularUserDAO loggedUser = em.createQuery(cr).getSingleResult();
        return Optional.ofNullable(loggedUser);
    }

    private Optional<RegularUserDAO> saveUser(RegularUserDAO user) {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<RegularUserDAO> saveRegularUser(UserDTO user) {
        RegularUserDAO u = new RegularUserDAO(user.getEmail(), user.getPassword());
        return saveUser(u);
    }
}
