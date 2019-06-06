package com.example.samazon.security;

import com.example.samazon.chau.Cart;
import com.example.samazon.chau.CartRepository;
import com.example.samazon.jacob.Address;
import com.example.samazon.jacob.AddressRepository;
import com.example.samazon.jacob.Product;
import com.example.samazon.jacob.ProductRepository;
import com.example.samazon.jin.History;
import com.example.samazon.jin.HistoryRepository;
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
    AddressRepository addressRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    CartRepository cartRepository;

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

        //Sam
        user = new User("sam@smail.com", passwordEncoder.encode("password"), "Sam", "Sammy", true, "sam");

        Address address = new Address("1234 Sammas St", "Samington", "Washington", "243342");
        addressRepository.save(address);

        Product product = new Product("Vegetables Unleashed: A Cookbook by Jose Andres", "Books", 29.58, "Vegetables Unleashed is a new cookbook that will transform how we think about—and eat—the vast universe of vegetables. ", "https://res.cloudinary.com/db9bfssj4/image/upload/v1559761161/book.vegetables_zoy3ew.jpg"  );
        productRepository.save(product);

//        Cart cart = new Cart(user, product);
//        cartRepository.save(cart);

//        History history = new History();
//        history.setUser(user);
//        history.setProducts(product);

        user.setAddress(address);
//        user.setCarts(cart);
//        user.setHistory(history);
        userRepository.save(user);






        product= new Product("Siege: Trump under Fire", "Books", 17.99, "Michael Wolff, author of the bombshell bestseller Fire and Fury, once again takes us inside the Trump presidency to reveal a White House under siege.", "https://res.cloudinary.com/db9bfssj4/image/upload/v1559761323/book.siege_eeglsd.jpg"  );
        productRepository.save(product);

        product= new Product("Anthony Bourdain Remembered", "Books", 21.00, "A moving and insightful collection of quotes, memories, and images celebrating the life of Anthony Bourdain  ", "https://res.cloudinary.com/db9bfssj4/image/upload/v1559765083/book.anthony_acolkn.jpg"  );
        productRepository.save(product);

        product= new Product("The Total Money Makeover: Classic Edition: A Proven Plan for Financial Fitness", "Books", 16.99, "With The Total Money Makeover: Classic Edition, you’ll be able to: Design a sure-fire plan for paying off all debt—meaning cars, houses, everything, as well as recognize the 10 most dangerous money myths  ", "https://res.cloudinary.com/db9bfssj4/image/upload/v1559765422/dave_gki3bf.jpg"  );
        productRepository.save(product);

        product= new Product("Fossil Men’s Grant Sport Stainless Steel and Leather Chronograph Quarts Watch", "Men's Fashion", 92.87, "•\tCase size: 44mm; Band size: 22mm; quartz movement with luminous 3-hand analog display; mineral crystal face; imported\n" +
                "•\tRound gold tone plated stainless steel case with blue dial and Roman numerals", "https://res.cloudinary.com/db9bfssj4/image/upload/v1559766052/6f2a02c3-3392-47a3-b70c-ba7f9e6864af._CR712_164_1636_3273_PT0_SX150___iht1ob.jpg"  );
        productRepository.save(product);

        product= new Product("Under Armour Men’s Tech Polo ", "Men's Fashion", 59.99, "•\t100% Polyester\n" +
                "•\tImported\n" +
                "•\tMachine Wash", "https://res.cloudinary.com/db9bfssj4/image/upload/v1559766586/31OF59vl5fL._AC_SR160_200__s5oovj.jpg"  );
        productRepository.save(product);

        product= new Product("IZOD Men’s Saltwater Stretch Flat Front Straight Fir Chino Pants", "Men's Fashion", 45.65, "•\t98% Cotton, 2% Spandex\n" +
                "•\tImported\n" +
                "•\tZipper closure", "https://res.cloudinary.com/db9bfssj4/image/upload/v1559766703/fashion.pant_ka0if8.jpg"  );
        productRepository.save(product);

        product= new Product("Adidas Men’s Lite Racer BYD Running shoe ", "Men's Fashion", 98.99, "•\t100% Polyester\n" +
                "•\tImported", "https://res.cloudinary.com/db9bfssj4/image/upload/v1559766916/818Ck4QSMDL._UX395__ni5g6o.jpg"  );
        productRepository.save(product);

        product= new Product("Nikon D850 FX-Format Digital SLR Camera Body", "Electronics", 2996.00, "•\tNikon-designed back-side illuminated (BSI) full-frame image sensor with no optical low-pass filter", "https://res.cloudinary.com/db9bfssj4/image/upload/v1559767116/51mo908e-4L._SL500_AC_SS350__pvbsy6.jpg"  );
        productRepository.save(product);

        product= new Product("HyperX Cloud - Official Playstation Licensed Gaming Headset", "Electronics", 69.95, "•\tOfficial PlayStation Licensed Gaming Headset\n" +
                "•\tSignature HyperX comfort\n" +
                "•\tDurable aluminum frame\n", "https://res.cloudinary.com/db9bfssj4/image/upload/v1559767269/8fa3df67-91b5-4c13-acaf-edb2e9d3edf7._CR0_0_1000_1000_PT0_SX300___vxlevd.jpg"  );
        productRepository.save(product);


        product= new Product("Ultimate Ears BLAST Portable Waterproof Wi-Fi and Bluetooth Speaker", "Electronics", 79.99, "•\tUltimate Sound: Super-portable with 360° bold, immersive, crystal clear sound and up to 12 hours of battery. It brings your music to life wherever you take it", "https://res.cloudinary.com/db9bfssj4/image/upload/v1559767463/4abb010e-135e-4bfb-b7ed-47a509ae73c0._CR0_0_200_225_SX200___aljcza.jpg"  );
        productRepository.save(product);

        product= new Product("All-New Fire 7 Tablet", "Electronics", 59.99, "•\t7\" IPS display; 16 or 32 GB of internal storage (add up to 512 GB with microSD)\n" +
                "•\tFaster 1.3 GHz quad-core processor\n" +
                "•\tUp to 7 hours of reading, browsing the web, watching video, and listening to music\n" +
                "•\tNow Alexa hands-free", "https://res.cloudinary.com/db9bfssj4/image/upload/v1559767739/f8k_comp._CB486326749__kfyocr.png"  );
        productRepository.save(product);





//        name;
//        this.category = category;
//        this.price = price;
//        this.description = description;
//        this.image = image;
    }
}

