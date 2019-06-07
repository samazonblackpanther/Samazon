package com.example.samazon.jacob;

import com.example.samazon.security.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "wishlist")
    private User user;

    @ManyToMany
    private Collection<Product> products;

    public Wishlist() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Product product) {
        products.add(product);
    }
}
