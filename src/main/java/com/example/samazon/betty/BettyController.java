package com.example.samazon.betty;

import com.example.samazon.chau.CartRepository;
import com.example.samazon.chau.CartService;
import com.example.samazon.jacob.Product;
import com.example.samazon.jacob.ProductRepository;
import com.example.samazon.jacob.WishlistRepository;
import com.example.samazon.jacob.WishlistService;
import com.example.samazon.security.CloudinaryConfig;
import com.example.samazon.security.User;
import com.example.samazon.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BettyController {
   @Autowired
    ProductRepository productRepository;

   @Autowired
    private UserService userService;

   @Autowired
   private CartService cartService;

   @Autowired

   private WishlistRepository wishlistRepository;

   @Autowired
   private WishlistService wishlistService;



    @RequestMapping("/home")
    public String homePage(Model model){
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("user", userService.getCurrentUser());
//

        return "security/index";
    }

//    @PostMapping("/home")
//    public String postHomepage(@RequestParam("cat") String cat, Model model){
//
//        model.addAttribute("products", productRepository.findByName(cat));
//        model.addAttribute("allproducts", productRepository.findAll());
//
//        return "security/index";
//    }

    @RequestMapping("/productlist")
    public String home(Model model){

        User user = userService.getCurrentUser();


        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("carts", user.getCarts());

        if (user != null){
            if (user.getCarts() == null){
                cartService.genCart(userService.getCurrentUser());
            }
        }


        if (user != null){
            if (user.getWishlist() == null){
                wishlistService.genWish(userService.getCurrentUser());
            }
        }
//        model.addAttribute("cart", userService.getCurrentUser().getCarts());
//        model.addAttribute("products", userService.getCurrentUser().getCarts().getProducts());



        return "betty/productlist";
    }

    @RequestMapping("/productdetails")
    public String productDetails(Model model){

        User user = userService.getCurrentUser();


        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("user", userService.getCurrentUser());

        //For shopping cart
        model.addAttribute("cart", userService.getCurrentUser().getCarts());
        model.addAttribute("products", userService.getCurrentUser().getCarts().getProducts());



        return "betty/productdetails";
    }
}
