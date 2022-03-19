package com.example.demo.controller;

import com.example.demo.model.dao.PackageDAO;
import com.example.demo.model.dto.PackageDTO;
import com.example.demo.service.PackageService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.util.List;

public class PackageController {
    private final PackageService _packageService = new PackageService();
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD1");

    public PackageDAO createPackage(PackageDTO packageDTO) {
        try {
            return _packageService.createPackage(packageDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PackageDAO deletePackage(Long id) {
        try {
            return _packageService.deletePackage(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PackageDAO updatePackage(Long id, PackageDTO packageDTO) {
        try {
            return _packageService.updatePackage(id, packageDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PackageDAO> getAllPackages() {
        try {
            return _packageService.getAllPackages();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


//    public List<PackageDAO> getFilteredPackages(Double minPrice, Double maxPrice, Date startDate, Date endDate) {
//        try {
//            return _packageService.getFilteredPackages(minPrice, maxPrice, startDate, endDate);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public PackageDAO bookVacation(Long userID, Long packageID) {
        try {
            return _packageService.bookVacation(userID, packageID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PackageDAO> getPackagesByAgencyID(Long agencyID) {
        try {
            return _packageService.getPackagesByAgencyID(agencyID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        PackageController pc = new PackageController();
        PackageDTO p = new PackageDTO("ba", 214d, new Date(12412412421L), new Date(1241241242122L), "detalii" , 2L, 12L, 124);
        pc.createPackage(p);
        System.out.println(pc.getPackagesByAgencyID(12L).get(1).getName());
    }
}
