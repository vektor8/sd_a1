package com.example.demo.model.dao;


import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "package")
public class PackageDAO {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private DestinationDAO destination;

    public void setAgency(AgencyDAO agency) {
        this.agency = agency;
    }

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private String extraDetails;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private AgencyDAO agency;

    public void setDestination(DestinationDAO destination) {
        this.destination = destination;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setExtraDetails(String extraDetails) {
        this.extraDetails = extraDetails;
    }

    public void setAvailablePlaces(Integer availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public void setTourists(List<RegularUserDAO> tourists) {
        this.tourists = tourists;
    }

    public PackageDAO(DestinationDAO destination, String name, Double price, Date startDate, Date endDate, String extraDetails, AgencyDAO agency, Integer availablePlaces) {
        this.destination = destination;
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extraDetails = extraDetails;
        this.agency = agency;
        this.availablePlaces = availablePlaces;
        this.tourists = new ArrayList<>();
    }

    @Column
    public Integer availablePlaces;

    @ManyToMany(mappedBy = "bookings")
    private List<RegularUserDAO> tourists;

    public PackageDAO() {

    }

    public void addTourist(RegularUserDAO tourist){
        this.tourists.add(tourist);
    }
    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getDestination() {
        return destination.getName();
    }

    public Double getPrice() {
        return price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getExtraDetails() {
        return extraDetails;
    }

    public String getAgency() {
        return agency.getName();
    }

    public Integer getAvailablePlaces() {
        return availablePlaces - tourists.size();
    }

}
