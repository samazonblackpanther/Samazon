package com.example.samazon.jacob;

import javax.persistence.*;
import com.example.samazon.security.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import com.example.samazon.chau.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    @Size(min = 2)
    private String category;


    private double price;

    @NotNull
    @Size(min = 2)
    private String description;

    private String image;

    private int quantity;

    @ManyToMany
    private Collection<User> customer;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<Cart> carts;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<Wishlist> wishlists;

    public Product() {
        this.carts=new HashSet<>();
    }

    public Product(@NotNull @Size(min = 2) String name, @NotNull @Size(min = 2) String category, double price, @NotNull @Size(min = 2) String description, String image) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<User> getCustomer() {
        return customer;
    }

    public void setCustomer(Collection<User> customer) {
        this.customer = customer;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Set<Wishlist> getWishlists() {
        return wishlists;
    }

    public void setWishlists(Set<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }
}
