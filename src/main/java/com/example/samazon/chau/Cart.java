package com.example.samazon.chau;

import com.example.samazon.security.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import com.example.samazon.jacob.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @OneToOne(mappedBy = "carts")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<Product> products;

    public Cart() {
    }

    public Cart(User user, Product product) {
        this.user = user;
        products.add(product);
    }

    //    public Cart() {
//        // this.users = new HashSet<>();
//        this.products = new HashSet<>();
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Product product) {
        products.add(product);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}