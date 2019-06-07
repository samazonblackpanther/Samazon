package com.example.samazon.jin;

import com.example.samazon.chau.CartRepository;
import com.example.samazon.chau.CartService;
import com.example.samazon.jacob.AddressRepository;
import com.example.samazon.jacob.ProductRepository;
import com.example.samazon.security.UserRepository;
import com.example.samazon.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Properties;

@Controller
public class JinController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    UserService userService;

    @Autowired
    HistoryService historyService;

    @Autowired
    CartService cartService;

//================== user


    @RequestMapping("/detailUser")
    public  String Home(Model model){
        model.addAttribute("user", userService.getCurrentUser());


        // Shopping Cart
        if (userService.getCurrentUser() != null){
            //For Shopping Cart
            int cartCount = 0;

            if (userService.getCurrentUser().getCarts() != null){
                cartCount += cartService.countItems(userService.getCurrentUser().getCarts());
            }
            model.addAttribute("cart", userService.getCurrentUser().getCarts());
            model.addAttribute("cartnumber", cartCount);
        }

        return "Jin/detailUser";
}

    @RequestMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") long id, Model model){
        model.addAttribute("user", userRepository.findById(id).get());

        // Shopping Cart
        if (userService.getCurrentUser() != null){
            //For Shopping Cart
            int cartCount = 0;

            if (userService.getCurrentUser().getCarts() != null){
                cartCount += cartService.countItems(userService.getCurrentUser().getCarts());
            }
            model.addAttribute("cart", userService.getCurrentUser().getCarts());
            model.addAttribute("cartnumber", cartCount);
        }


        return "security/registration";
    }

//================= manage product for admin

    @RequestMapping("/editProduct/{id}")
    public String addProduct(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepository.findById(id).get());
        return "jacob/addproduct";
    }

    @RequestMapping("/changePrice/{id}")
    public String changePrice(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepository.findById(id).get());
        return "jacob/addproduct";
    }

//==================== order for user

    @RequestMapping("/showOrderHistory")
    public String listCarts(Model model){

        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("products", userService.getCurrentUser().getHistory().getProducts());
        model.addAttribute("cart", userService.getCurrentUser().getCarts());


        // Shopping Cart
        if (userService.getCurrentUser() != null){
            //For Shopping Cart
            int cartCount = 0;

            if (userService.getCurrentUser().getCarts() != null){
                cartCount += cartService.countItems(userService.getCurrentUser().getCarts());
            }
            model.addAttribute("cart", userService.getCurrentUser().getCarts());
            model.addAttribute("cartnumber", cartCount);
        }


        return "Jin/showOrderHistory";
    }

}
