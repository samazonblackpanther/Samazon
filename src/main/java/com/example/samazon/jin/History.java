package com.example.samazon.jin;

import com.example.samazon.jacob.Product;
import com.example.samazon.security.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;

    @OneToOne(mappedBy = "history")
    private User user;

    @ManyToMany
    private Collection<Product> products;

    public History() {
    }

    public History(User user, Collection<Product> products) {
        this.user = user;
        this.products = products;
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

//        this.products = products;
//    public void setProducts(Collection<Product> products) {
//    }
}
