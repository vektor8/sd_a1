package com.example.demo.model.dto;

import java.util.Date;

public class PackageDTO {
    private String name;

    private Double price;

    private Date startDate;

    private Date endDate;

    private String extraDetails;

    private Long destinationID;

    private Long agencyID;

    private Integer availablePlaces;


    public Long getAgencyID() {
        return agencyID;
    }

    public PackageDTO(String name, Double price, Date startDate, Date endDate, String extraDetails, Long destinationID, Long agencyID, Integer availablePlaces) {
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extraDetails = extraDetails;
        this.destinationID = destinationID;
        this.agencyID = agencyID;
        this.availablePlaces = availablePlaces;
    }

    public Integer getAvailablePlaces() {
        return availablePlaces;
    }

    public String getName() {
        return name;
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

    public Long getDestinationID() {
        return destinationID;
    }
}
