package com.example.samazon.jacob;

import com.example.samazon.chau.CartService;
import com.example.samazon.security.User;
import com.example.samazon.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collection;

@Service
public class ShoppingCartService {

    @Autowired
    UserService userService;
    CartService cartService;

    public void shoppingCartLoader(User user, Model model){
        // Shopping Cart
        if (userService.getCurrentUser() != null){
            //For Shopping Cart
            int cartCount = 0;
//            Collection<Product> cartProduct = user.getCarts().getProducts();
//            if (cartProduct != null){
//                cartCount += cartService.countItems(userService.getCurrentUser().getCarts());
//            }
            model.addAttribute("cart", userService.getCurrentUser().getCarts());
            model.addAttribute("cartnumber", cartCount);
        }
        model.addAttribute("user", userService.getCurrentUser());
        if (userService.getCurrentUser() != null){
            model.addAttribute("history", userService.getCurrentUser().getHistory());
        }
    }
}
