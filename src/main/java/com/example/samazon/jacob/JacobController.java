package com.example.samazon.jacob;

import com.example.samazon.security.RoleRepository;
import com.example.samazon.security.User;
import com.example.samazon.security.UserRepository;
import com.example.samazon.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class JacobController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserService userService;



    @GetMapping("/addproduct")
    public String showProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "security/registration";
    }

    @PostMapping("/postproduct")
    public String processProductPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        model.addAttribute("user",user);
        if(result.hasErrors()) {
            return "/security/registration";
        }
        else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "security/index";
    }
}
