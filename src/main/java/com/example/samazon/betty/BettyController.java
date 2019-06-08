package com.example.samazon.betty;

import com.example.samazon.chau.CartRepository;
import com.example.samazon.chau.CartService;
import com.example.samazon.jacob.*;
import com.example.samazon.security.CloudinaryConfig;
import com.example.samazon.security.User;
import com.example.samazon.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

   @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping("/home")
    public String homePage(Model model){
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("user", userService.getCurrentUser());
//

        User user = userService.getCurrentUser();
        shoppingCartService.shoppingCartLoader(model);

        return "security/index";
    }



    @RequestMapping("/productlist")
    public String home(Model model, @RequestParam("category") int cat){

        User user = userService.getCurrentUser();

        shoppingCartService.shoppingCartLoader(model);

        switch (cat){
            case 1:
                model.addAttribute("message", "All Books");
                model.addAttribute("products", productRepository.findAllByCategory("Books"));
                break;
            case 2:
                model.addAttribute("message", "Electronics Products");
                model.addAttribute("products", productRepository.findAllByCategory("Electronics"));
                break;
            case 3:
                model.addAttribute("message", "Men's Fashion Products");
                model.addAttribute("products", productRepository.findAllByCategory("Men's Fashion"));
                break;
            case 4:
                model.addAttribute("message", "Kitchen Products");
                model.addAttribute("products", productRepository.findAllByCategory("Kitchen"));
                break;
            case 5:
                model.addAttribute("message", "Health & Wellness Products");
                model.addAttribute("products", productRepository.findAllByCategory("Health & Wellness"));
                break;
            case 6:
                model.addAttribute("message", "All Products");
                model.addAttribute("products", productRepository.findAll());
                break;


        }
        model.addAttribute("user", userService.getCurrentUser());



        if (user != null){
            if (user.getCarts() == null){
                model.addAttribute("carts", user.getCarts());
                cartService.genCart(userService.getCurrentUser());
            }
        }


        if (user != null){
            if (user.getWishlist() == null){
                wishlistService.genWish(userService.getCurrentUser());
            }
        }


        return "betty/productlist";
    }

    @RequestMapping("/productlistnav")
    public String proListNav(Model model){

        User user = userService.getCurrentUser();

        shoppingCartService.shoppingCartLoader(model);

        model.addAttribute("message", "All Products");
        model.addAttribute("products", productRepository.findAll());


        model.addAttribute("user", userService.getCurrentUser());



        if (user != null){
            if (user.getCarts() == null){
                model.addAttribute("carts", user.getCarts());
                cartService.genCart(userService.getCurrentUser());
            }
        }


        if (user != null){
            if (user.getWishlist() == null){
                wishlistService.genWish(userService.getCurrentUser());
            }
        }


        return "betty/productlist";
    }

    @RequestMapping("/productdetails/{id}")
    public String productDetails(@PathVariable("id") long id, Model model){

        User user = userService.getCurrentUser();
        if (user != null){
            if (user.getCarts() == null){
                model.addAttribute("carts", user.getCarts());
                cartService.genCart(userService.getCurrentUser());
            }
        }


        if (user != null){
            if (user.getWishlist() == null){
                wishlistService.genWish(userService.getCurrentUser());
            }
        }

        shoppingCartService.shoppingCartLoader(model);

        model.addAttribute("product", productRepository.findById(id).get());
        model.addAttribute("user", user);





        return "betty/productdetails";
    }
}
