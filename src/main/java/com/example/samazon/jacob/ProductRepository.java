package com.example.samazon.jacob;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ProductRepository extends CrudRepository<Product, Long>{
    Product findByName(String name);
    ArrayList<Product> findAllByNameContainingIgnoreCase(String name);



    Iterable<Product> findAllByCategory(String category);

}
