package com.example.demo.repository;

import com.example.demo.model.dao.AgencyDAO;
import com.example.demo.model.dao.DestinationDAO;
import com.example.demo.model.dao.PackageDAO;
import com.example.demo.model.dao.RegularUserDAO;
import com.example.demo.model.dto.DestinationDTO;
import com.example.demo.model.dto.PackageDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class PackageRepository {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD1");

    public Optional<PackageDAO> createPackage(PackageDTO packageDTO) {

        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            System.out.println(em.find(AgencyDAO.class, packageDTO.getAgencyID()));
            PackageDAO packageDAO = new PackageDAO(em.find(DestinationDAO.class, packageDTO.getDestinationID()),
                    packageDTO.getName(), packageDTO.getPrice(), packageDTO.getStartDate(), packageDTO.getEndDate(),
                    packageDTO.getExtraDetails(), em.find(AgencyDAO.class, packageDTO.getAgencyID()), packageDTO.getAvailablePlaces());
            em.getTransaction().begin();
            em.persist(packageDAO);
            em.getTransaction().commit();
            return Optional.of(packageDAO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<PackageDAO> deletePackage(Long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        PackageDAO packageDAO = em.find(PackageDAO.class, id);
        em.remove(packageDAO);
        em.getTransaction().commit();
        em.close();
        return Optional.ofNullable(packageDAO);
    }

    public Optional<PackageDAO> updatePackage(Long id, PackageDTO packageDTO) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        PackageDAO packageDAO = em.find(PackageDAO.class, id);
        packageDAO.setName(packageDTO.getName());
        packageDAO.setPrice(packageDTO.getPrice());
        packageDAO.setStartDate(packageDTO.getStartDate());
        packageDAO.setEndDate(packageDTO.getEndDate());
        packageDAO.setDestination(em.find(DestinationDAO.class, packageDTO.getDestinationID()));
        packageDAO.setAgency(em.find(AgencyDAO.class, packageDTO.getAgencyID()));
        packageDAO.setExtraDetails(packageDTO.getExtraDetails());
        packageDAO.setAvailablePlaces(packageDTO.getAvailablePlaces());

        em.getTransaction().commit();
        em.close();
        return Optional.of(packageDAO);
    }

    public Optional<List<PackageDAO>> getAllPackages() {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PackageDAO> cr = cb.createQuery(PackageDAO.class);
        Root<PackageDAO> root = cr.from(PackageDAO.class);
        cr.select(root);
        return Optional.ofNullable(em.createQuery(cr).getResultList());
    }

    public Optional<List<PackageDAO>> getFilteredPackages(Double minPrice, Double maxPrice, Date startDate, Date endDate, Long destinationID){
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PackageDAO> cr = cb.createQuery(PackageDAO.class);
        Root<PackageDAO> root = cr.from(PackageDAO.class);

        Predicate price = cb.between(root.get("price"), minPrice, maxPrice);
        Predicate startDatePred = cb.greaterThanOrEqualTo(root.get("startDate"), startDate);
        Predicate endDatePred = cb.lessThanOrEqualTo(root.get("endDate"), endDate);
        Predicate destinationPred = cb.equal(root.get("destination"), destinationID);

        cr.select(root).where(cb.and(price, startDatePred, endDatePred, destinationPred));
        List<PackageDAO> result = em.createQuery(cr).getResultList();
        return Optional.ofNullable(result);
    }

    public Optional<List<PackageDAO>> getFilteredPackages(Double minPrice, Double maxPrice, Date startDate, Date endDate){
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PackageDAO> cr = cb.createQuery(PackageDAO.class);
        Root<PackageDAO> root = cr.from(PackageDAO.class);

        Predicate price = cb.between(root.get("price"), minPrice, maxPrice);
        Predicate startDatePred = cb.greaterThanOrEqualTo(root.get("startDate"), startDate);
        Predicate endDatePred = cb.lessThanOrEqualTo(root.get("endDate"), endDate);

        cr.select(root).where(cb.and(price, startDatePred, endDatePred));
        List<PackageDAO> result = em.createQuery(cr).getResultList();
        return Optional.ofNullable(result);
    }

    public Optional<PackageDAO> bookVacation(Long userID, Long packageID){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        PackageDAO packageDAO = em.find(PackageDAO.class, packageID);
        RegularUserDAO tourist = em.find(RegularUserDAO.class, userID);
        packageDAO.addTourist(tourist);
        tourist.addBooking(packageDAO);

        em.getTransaction().commit();
        em.close();
        return Optional.of(packageDAO);
    }

    public Optional<List<PackageDAO>> getPackagesByAgencyID(Long agencyID){
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PackageDAO> cr = cb.createQuery(PackageDAO.class);
        Root<PackageDAO> root = cr.from(PackageDAO.class);

        cr.select(root).where(cb.equal(root.get("agency"), agencyID));
        List<PackageDAO> result = em.createQuery(cr).getResultList();
        return Optional.ofNullable(result);
    }
}
