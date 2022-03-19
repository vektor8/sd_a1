package com.example.demo.repository;

import com.example.demo.model.dao.DestinationDAO;
import com.example.demo.model.dao.PackageDAO;
import com.example.demo.model.dto.DestinationDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class DestinationRepository {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD1");

    public Optional<DestinationDAO> createDestination(DestinationDTO destinationDTO) {
        DestinationDAO destinationDAO = new DestinationDAO(destinationDTO.getName());
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            em.persist(destinationDAO);
            em.getTransaction().commit();
            return Optional.of(destinationDAO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<DestinationDAO> deleteDestination(Long id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        DestinationDAO destination = em.find(DestinationDAO.class, id);
        em.remove(destination);
        em.getTransaction().commit();
        em.close();
        return Optional.ofNullable(destination);
    }

    public Optional<List<DestinationDAO>> getAllDestinations(){
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DestinationDAO> cr = cb.createQuery(DestinationDAO.class);
        Root<DestinationDAO> root = cr.from(DestinationDAO.class);
        cr.select(root);
        return Optional.ofNullable(em.createQuery(cr).getResultList());
    }
}
