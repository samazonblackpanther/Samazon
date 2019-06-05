package com.example.samazon.chau;

import com.example.samazon.jacob.Product;
import com.example.samazon.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChauController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

//    @RequestMapping("/addcart{id}")
//    public String addCart(@PathVariable("id") long id, Model model){
//       User user = userService.getCurrentUser();
//        Product product = productService.getProduct(id);
//        Cart activeCart = cartService.updateCart(product, user);
//        model.addAttribute("cart", activeCart);
//        return "redirect:/cart";
//    }




}
