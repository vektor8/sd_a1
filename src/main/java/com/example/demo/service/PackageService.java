package com.example.demo.service;

import com.example.demo.model.dao.PackageDAO;
import com.example.demo.model.dto.PackageDTO;
import com.example.demo.repository.PackageRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class PackageService {
    private static final PackageRepository _packageRepository = new PackageRepository();

    private boolean validPackage(PackageDTO pack){
        return pack.getPrice() >= 0 && pack.getStartDate().compareTo(pack.getEndDate()) <= 0;
    }
    public PackageDAO createPackage(PackageDTO packageDTO) throws Exception {
        if(!validPackage(packageDTO)){
            throw new Exception("package data is not correct");
        }
        Optional<PackageDAO> newPackage =_packageRepository.createPackage(packageDTO);
        if (newPackage.isPresent()) {
            return newPackage.get();
        } else {
            throw new Exception("new package could not be added to db");
        }
    }

    public PackageDAO deletePackage(Long id) throws Exception {
        Optional<PackageDAO> newPackage =_packageRepository.deletePackage(id);
        if (newPackage.isPresent()) {
            return newPackage.get();
        } else {
            throw new Exception("Package could not be deleted");
        }
    }

    public PackageDAO updatePackage(Long id, PackageDTO packageDTO) throws Exception {
        if(!validPackage(packageDTO)){
            throw new Exception("package data is not correct");
        }
        Optional<PackageDAO> updatedPackage =_packageRepository.updatePackage(id, packageDTO);
        if (updatedPackage.isPresent()) {
            return updatedPackage.get();
        } else {
            throw new Exception("Package could not be deleted");
        }
    }

    public List<PackageDAO> getAllPackages() throws Exception {
        Optional<List<PackageDAO>> packages = _packageRepository.getAllPackages();
        if(packages.isPresent()){
            return packages.get();
        }
        else{
            throw new Exception("No packages were found");
        }
    }

    public List<PackageDAO> getFilteredPackages(Double minPrice, Double maxPrice, Date startDate, Date endDate, Long destinationID) throws Exception {
        Optional<List<PackageDAO>> packages = _packageRepository.getFilteredPackages(minPrice, maxPrice, startDate, endDate, destinationID);
        System.out.println(minPrice +" " + maxPrice + " " + startDate + " " + endDate +" " + destinationID);
        if(packages.isPresent()){
            return packages.get();
        }
        else{
            throw new Exception("No packages were found");
        }
    }

    public List<PackageDAO> getFilteredPackages(Double minPrice, Double maxPrice, Date startDate, Date endDate) throws Exception {
        Optional<List<PackageDAO>> packages = _packageRepository.getFilteredPackages(minPrice, maxPrice, startDate, endDate);
        if(packages.isPresent()){
            return packages.get();
        }
        else{
            throw new Exception("No packages were found");
        }
    }
    public PackageDAO bookVacation(Long userID, Long packageID) throws Exception {
        Optional<PackageDAO> updatedPackage =_packageRepository.bookVacation(userID, packageID);
        if (updatedPackage.isPresent()) {
            return updatedPackage.get();
        } else {
            throw new Exception("Package could not be deleted");
        }
    }

    public List<PackageDAO> getPackagesByAgencyID(Long agencyID)throws  Exception{
        Optional<List<PackageDAO>> updatedPackage =_packageRepository.getPackagesByAgencyID(agencyID);
        if (updatedPackage.isPresent()) {
            return updatedPackage.get();
        } else {
            throw new Exception("Package could not be deleted");
        }
    }
}
