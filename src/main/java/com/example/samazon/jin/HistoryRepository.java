package com.example.samazon.jin;

import com.example.samazon.jacob.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface HistoryRepository extends CrudRepository<History, Long>{

//    ArrayList<History> findAllByProductsContains_NameContainingIgnoreCase(String name);
}
