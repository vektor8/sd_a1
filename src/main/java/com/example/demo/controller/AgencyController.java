package com.example.demo.controller;

import com.example.demo.model.dao.DestinationDAO;
import com.example.demo.model.dao.PackageDAO;
import com.example.demo.model.dto.DestinationDTO;
import com.example.demo.model.dto.PackageDTO;
import com.example.demo.service.DestinationService;
import com.example.demo.service.PackageService;

import java.util.List;

public class AgencyController {
    private final DestinationService _destinationService = new DestinationService();
    private final PackageService _packageService = new PackageService();

    public DestinationDAO createDestination(DestinationDTO destinationDTO){
        try {
            return _destinationService.createDestination(destinationDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public DestinationDAO deleteDestination(Long id){
        try {
            return _destinationService.deleteDestination(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
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

    public List<PackageDAO> getPackagesByAgencyID(Long agencyID) {
        try {
            return _packageService.getPackagesByAgencyID(agencyID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DestinationDAO> getAllDestinations() {
        try {
            return _destinationService.getAllDestinations();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args){
        AgencyController dc = new AgencyController();
//        DestinationDAO a = dc.createDestination(new DestinationDTO("Papa1"));
        dc.deleteDestination(4L);
    }
}
