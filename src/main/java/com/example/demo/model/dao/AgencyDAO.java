package com.example.demo.model.dao;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "agency")
public class AgencyDAO  {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private String password;

    @OneToMany(mappedBy = "agency")
    private List<PackageDAO> packages;

    public AgencyDAO(String email, String password) {
        this.name = email;
        this.password = password;
    }

    public AgencyDAO() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
