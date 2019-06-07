package com.example.samazon.security;

import com.example.samazon.jacob.Product;
import com.example.samazon.jacob.Address;
import com.example.samazon.jacob.Wishlist;
import com.example.samazon.jin.History;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;
import com.example.samazon.chau.*;

@Entity
@Table(name = "USER_DATA")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "username")
    private String username;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;

    @ManyToMany(mappedBy = "customer")
    private Collection<Product> products;

    @OneToOne(cascade = CascadeType.ALL)
    private Cart carts;

    @OneToOne(cascade = CascadeType.ALL)
    private Wishlist wishlist;
//
//    @OneToOne
//    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    private History history;

    @NotNull
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

    public User() {
    }

//    public User(String email, String password, String firstName, String lastName, boolean enabled, String username) {
//        this.setEmail(email);
//        this.setPassword(password);
//        this.setFirstName(firstName);
//        this.setLastName(lastName);
//        this.setEnabled(enabled);
//        this.setUsername(username);
//    }


    public User(String email, String password, String firstName, String lastName, boolean enabled, String username) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        this.username = username;
    }

    public User(String email, String password, String firstName, String lastName, boolean enabled, String username, @NotNull String streetAddress, @NotNull @Size(min = 2) String city, @NotNull @Size(min = 2) String state, @NotNull @Size(min = 5) String zipcode) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        this.username = username;
        this.history = history;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

//    public void setPassword(String password) {
//        BCryptPasswordEncoder passwordEncoder =
//                new BCryptPasswordEncoder();
//        this.password = passwordEncoder.encode(password);
//    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Product product) {
        products.add(product);
    }

//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;

    }

    public Cart getCarts() {
        return carts;
    }

    public void setCarts(Cart carts) {
        this.carts = carts;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
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
}