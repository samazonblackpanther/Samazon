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

    public void updateCart(Product product, User user){

        Cart carts = user.getCarts();
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

    public void cartHistory(Cart cart){
        for(Product product: cart.getProducts()){
            History history = new History();
            historyRepository.save(history);
            cart.getProducts().remove(product);
        }

    }
}
