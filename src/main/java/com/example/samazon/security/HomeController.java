package com.example.samazon.security;

import com.example.samazon.chau.CartRepository;
import com.example.samazon.chau.CartService;
import com.example.samazon.jacob.Address;
import com.example.samazon.jacob.AddressRepository;
import com.example.samazon.jacob.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "security/registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, @RequestParam("role") String role,
                                          @RequestParam("streetAddress") String streetAddress, @RequestParam("city") String city,
                                          @RequestParam("state") String state, @RequestParam("zipcode") String zipcode) {
        model.addAttribute("user",user);
        if(result.hasErrors()) {
            return "/security/registration";
        }
        else {
            Address address = new Address();
            address.setStreetAddress(streetAddress);
            address.setCity(city);
            address.setState(state);
            address.setZipcode(zipcode);
            address.setUser(userService.getCurrentUser());
            addressRepository.save(address);

            user.setAddress(address);

            userService.saveUser(user, role);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "security/index";
    }

    @RequestMapping("/")
    public String index(Model model) {


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


        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("user", userService.getCurrentUser());
        return "security/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "security/login";
    }

    @RequestMapping("/secure")
    public String secure(Model model) {
        // Gets the currently logged in user and maps it to "user" in the Thymeleaf template
        model.addAttribute("user", userService.getCurrentUser());
        return "security/secure";
    }

}
