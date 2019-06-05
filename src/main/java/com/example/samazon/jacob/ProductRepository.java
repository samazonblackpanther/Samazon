package com.example.samazon.jacob;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>{
    Product findByName(String name);
    Iterable<Product> findAllByNameContainingIgnoreCase(String name);

    Iterable<Product> findAllByCategory(String category);

}
