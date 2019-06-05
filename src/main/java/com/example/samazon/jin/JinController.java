package com.example.samazon.jin;

import com.example.samazon.chau.CartRepository;
import com.example.samazon.jacob.AddressRepository;
import com.example.samazon.jacob.ProductRepository;
import com.example.samazon.security.UserRepository;
import com.example.samazon.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    UserService userService;


//================== user

    @RequestMapping("/detailUser/{id}")
    public String showUser(@PathVariable("id") long id, Model model){
//        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("user", userRepository.findById(id).get());
        return "detailUser";
    }

    @RequestMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") long id, Model model){
//        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("user", userRepository.findById(id).get());
        return "/secutiy/registration";
    }

//=================== Order

    @RequestMapping("/showOrderHistory/{id}")
    public String listCarts(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("carts", cartRepository.findAll());
//    if (userService.getCurrentUser() != null) {
//        model.addAttribute("user_id", userService.getCurrentUser().getId());
//    }
    return "showOrderHistory";
}


}
