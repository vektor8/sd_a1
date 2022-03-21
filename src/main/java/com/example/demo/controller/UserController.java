package com.example.demo.controller;

import com.example.demo.model.dao.DestinationDAO;
import com.example.demo.model.dao.PackageDAO;
import com.example.demo.model.dao.RegularUserDAO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.service.DestinationService;
import com.example.demo.service.PackageService;
import com.example.demo.service.UserService;

import java.sql.Date;
import java.util.List;

public class UserController {
    private final UserService _userService = new UserService();
    private final PackageService _packageService = new PackageService();
    private final DestinationService _destinationService = new DestinationService();

    public RegularUserDAO login(UserDTO user) {
        try {
            return _userService.login(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public RegularUserDAO newRegularUser(UserDTO user) {
        try {
            return _userService.newAccount(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public PackageDAO bookVacation(Long userID, Long packageID) {
        try {
            return _packageService.bookVacation(userID, packageID);
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

    public List<PackageDAO> getFilteredPackages(Double minPrice, Double maxPrice, Date startDate, Date endDate, Long destinationID) {
        try {
            return _packageService.getFilteredPackages(minPrice, maxPrice, startDate, endDate, destinationID);
        } catch (Exception e) {
            return null;
        }
    }

    public List<PackageDAO> getFilteredPackages(Double minPrice, Double maxPrice, Date startDate, Date endDate) {
        try {
            return _packageService.getFilteredPackages(minPrice, maxPrice, startDate, endDate);
        } catch (Exception e) {
            return null;
        }
    }
    public List<DestinationDAO> getAllDestinations() {
        try {
            return _destinationService.getAllDestinations();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public RegularUserDAO updateUser(Long id){
        try {
            return _userService.getUserByID(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
