package com.example.samazon.security;

import com.example.samazon.chau.CartRepository;
import com.example.samazon.chau.CartService;
import com.example.samazon.jacob.*;
import com.example.samazon.jin.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());


        return "security/registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result,  Model model, @RequestParam("role") String role, @RequestParam("password") String password){

        model.addAttribute("user",user);
        if(result.hasErrors()) {
            return "/security/registration";
        }
        else {
            if(userService.getCurrentUser() != null){
                user.setId(userService.getCurrentUser().getId());
                user.setPassword(password);
                userService.saveUser(user, role);
            } else {
                user.setPassword(password);
                userService.saveUser(user, role);
            }
        }

        // Shopping Cart
        shoppingCartService.shoppingCartLoader(model);

        return "security/index";
    }

    @RequestMapping("/")
    public String index(Model model) {


        User user = userService.getCurrentUser();
        shoppingCartService.shoppingCartLoader(model);

        return "security/index";
    }

    @RequestMapping ("/login")
    public String login() {

        return "security/login";
    }


    @RequestMapping("/secure")
    public String secure(Model model) {
        // Gets the currently logged in user and maps it to "user" in the Thymeleaf template
        model.addAttribute("user", userService.getCurrentUser());

        User user = userService.getCurrentUser();
        shoppingCartService.shoppingCartLoader(model);
        if (userService.getCurrentUser() != null){
            model.addAttribute("history", userService.getCurrentUser().getHistory());
        }

        return "security/secure";
    }


    // Instantiate both the user and role repository to invoke constructor methods
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
        Run method will be executed after the application context is
        loaded and right before the Spring Application run method is
        completed.
     */

//    @PostConstruct
//    public void filltables(){
//
//
//
//    }

}
