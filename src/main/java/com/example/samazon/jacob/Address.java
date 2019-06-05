package com.example.samazon.jacob;

import com.example.samazon.security.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 5)
    private String streetAddress;

    @NotNull
    @Size(min = 2)
    private String city;

    @NotNull
    @Size(min = 2)
    private String state;

    @NotNull
    @Size(min = 5)
    private String zipcode;

    @OneToOne
    private User user;

    public Address() {
    }

    public Address(@NotNull @Size(min = 5) String streetAddress, @NotNull @Size(min = 2) String city, @NotNull @Size(min = 2) String state, @NotNull @Size(min = 5) String zipcode) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
