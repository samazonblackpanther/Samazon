package com.example.samazon.jin;

import com.example.samazon.chau.Cart;
import com.example.samazon.chau.CartRepository;
import com.example.samazon.jacob.Product;
import com.example.samazon.security.User;
import com.example.samazon.security.UserRepository;
import com.example.samazon.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    CartRepository cartRepository;

    public void genHistory(User user){
        if (user.getHistory() == null){
            History history = new History();
            history.setUser(user);
            user.setHistory(history);
            userRepository.save(user);
        }
    }

    public void cartHistory(Cart cart){

        Iterator<Product> iterator = cart.getProducts().iterator();

        while (iterator.hasNext()){
            Product product = iterator.next();
            History history = userService.getCurrentUser().getHistory();
            history.setProducts(product);
            historyRepository.save(history);
//            cart.getProducts().remove(product);
            iterator.remove();

        }

        cartRepository.save(cart);
    }
}
