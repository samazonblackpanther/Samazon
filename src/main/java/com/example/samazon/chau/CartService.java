package com.example.samazon.chau;


import com.example.samazon.jin.History;
import com.example.samazon.jin.HistoryRepository;
import com.example.samazon.security.UserRepository;
import com.example.samazon.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.samazon.security.User;
import com.example.samazon.security.UserRepository;
import com.example.samazon.jacob.*;

import java.util.Set;

@Service
public class CartService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HistoryRepository historyRepository;

    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public void genCart(User user){

        Cart carts = new Cart();
        carts.setUser(user);
        user.setCarts(carts);
        userRepository.save(user);
//        cartRepository.save(carts);
        System.out.println(user.getFirstName());
    }

    public void updateCart(Product product, Cart carts){

        carts.setProducts(product);
        cartRepository.save(carts);

    }

    public int countItems(Cart cart){
        int count = cart.getProducts().size();
        return count;
    }

    public void removeItem(Product product, Cart cart){
        cart.getProducts().remove(product);
    }

    public double totals(Cart cart){
        double total = 0;
        for (Product product: cart.getProducts()){

            total += product.getPrice();

        }
        return total;
    }


}
