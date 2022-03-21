package com.example.demo.model.dao;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "User")
public  class RegularUserDAO {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    public RegularUserDAO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bookings",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "package_id")})
    private List<PackageDAO> bookings;

    public RegularUserDAO() {
        super();
    }

    public void addBooking(PackageDAO pack){
        this.bookings.add(pack);
    }

    public List<PackageDAO> getBookings() {
        return bookings;
    }

    public Long getId() {
        return id;
    }
}
