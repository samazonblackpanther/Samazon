package com.example.samazon.jacob;

import com.example.samazon.chau.Cart;
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

    public void shoppingCartLoader(Model model){
        // Shopping Cart
        if (userService.getCurrentUser() != null){

            User user = userService.getCurrentUser();
            //For Shopping Cart
            int cartCount = 0;
            if (user.getCarts() != null) {
                System.out.println(user.getCarts().getId());
                int cartProduct = user.getCarts().getProducts().size();
                if (cartProduct >= 1){
                    cartCount = cartCount + cartProduct;
                }
            }
            model.addAttribute("cart", user.getCarts());
            model.addAttribute("cartnumber", cartCount);

            model.addAttribute("user", userService.getCurrentUser());

            model.addAttribute("history", userService.getCurrentUser().getHistory());
        }
    }
}
