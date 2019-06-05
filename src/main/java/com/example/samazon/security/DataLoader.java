package com.example.samazon.security;

import com.example.samazon.jacob.Product;
import com.example.samazon.jacob.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner{
    // Instantiate both the user and role repository to invoke constructor methods
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ProductRepository productRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
        Run method will be executed after the application context is
        loaded and right before the Spring Application run method is
        completed.
     */
    @Override
    public void run(String... strings) throws Exception{
        System.out.println("Loading data...");

        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role userRole = roleRepository.findByRole("USER");
        Role adminRole = roleRepository.findByRole("ADMIN");

        User user = new User("bob@bob.com",passwordEncoder.encode("password"),"Bob","Bobberson",true,"bob");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("admin@adm.com",passwordEncoder.encode("password"),"Admin","User",true,"admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        Product product= new Product("Vegetables Unleashed: A Cookbook by Jose Andres", "Books", 29.58, "A NEW YORK TIMES BESTSELLER. From the endlessly inventive imaginations of star Spanish-American chef José Andrés.", "https://res.cloudinary.com/db9bfssj4/image/upload/v1559761161/book.vegetables_zoy3ew.jpg"  );
        productRepository.save(product);

        product= new Product("2.\tSiege: Trump under Fire", "Books", 17.99, "•\tMichael Wolff, author of the bombshell bestseller Fire and Fury, once again takes us inside the Trump presidency to reveal a White House under siege.", "https://res.cloudinary.com/db9bfssj4/image/upload/v1559761323/book.siege_eeglsd.jpg"  );
        productRepository.save(product);

//        name;
//        this.category = category;
//        this.price = price;
//        this.description = description;
//        this.image = image;
    }
}
