package com.example.demo.model.dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "destination")
public class DestinationDAO {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.REMOVE)
    private List<PackageDAO> packages;

    public DestinationDAO(String name) {
        this.name = name;
        this.packages = new ArrayList<>();
    }

    public DestinationDAO() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
